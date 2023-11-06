package com.example.miniaibackend.controller;

import cn.hutool.core.date.DateTime;
import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.mapper.MessageMapper;
import com.example.miniaibackend.models.AcceptDTO;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.listener.SseStreamListener;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/chat")
@CrossOrigin
public class ChatGptController {

    @Resource
    MessageMapper messageMapper;


    @PostMapping("/sse")
    @CrossOrigin
    public SseEmitter sendMsg(@RequestBody AcceptDTO acceptDTO) {
        Integer userId = acceptDTO.getUserId();
        int size = acceptDTO.getMessageList().size();
        com.plexpt.chatgpt.entity.chat.Message message = acceptDTO.getMessageList().get(size - 1);
        if(message.getRole().equals("user")) {
            String content = message.getContent();
            Integer conversationId = acceptDTO.getConversationId();
            DateTime userTime = new DateTime();
            Message userMessage = new Message(null, conversationId, userId, content, userTime);
            messageMapper.insert(userMessage);
        }

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
            String content = com.plexpt.chatgpt.entity.chat.Message.ofAssistant(msg).getContent();
            DateTime dateTime = new DateTime();
            Message botMessage = new Message(null, acceptDTO.getConversationId(),null,content,dateTime);
            messageMapper.insert(botMessage);
            return;
        });
        chatGPTStream.streamChatCompletion(chatCompletion, listener);
        return sseEmitter;
    }

}
