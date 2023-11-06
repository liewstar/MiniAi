package com.example.miniaibackend.utils;

import cn.hutool.core.date.DateTime;
import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.mapper.MessageMapper;
import com.example.miniaibackend.models.AcceptDTO;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


public class ChatGptUtils {
    public static MessageMapper messageMapper;;

    public static void setMessageMapper(MessageMapper mapper) {
        messageMapper = mapper;
    }

    public static SseEmitter response(AcceptDTO acceptDTO) {
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .apiKey(acceptDTO.getToken())
                .apiHost(acceptDTO.getEndpoint())
                .timeout(5000)
                .build()
                .init();

        ChatCompletion chatCompletion = ChatCompletion.builder()
 //              .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .model(acceptDTO.getModel())
                .messages(acceptDTO.getMessageList())
                .maxTokens(acceptDTO.getMaxToken())
                .temperature(acceptDTO.getTemperature())
                .presencePenalty(acceptDTO.getPresencePenalty())
                .frequencyPenalty(acceptDTO.getFrequencyPenalty())
                .build();
        SseEmitter sseEmitter = new SseEmitter(-1L);
        SseStreamListener listener = new SseStreamListener(sseEmitter);
        listener.setOnComplate(msg -> {
            System.out.println("the log has been cut");
            //这里是传输完成了
            //插入ai的聊天记录
            String content = com.plexpt.chatgpt.entity.chat.Message.ofAssistant(msg).getContent();
            DateTime dateTime = new DateTime();
            Message message = new Message(null, acceptDTO.getConversationId(),acceptDTO.getUserId(),content,dateTime);
            messageMapper.insert(message);
        });
        chatGPTStream.streamChatCompletion(chatCompletion, listener);
        return sseEmitter;
    }
}
