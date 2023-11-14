package com.example.miniaibackend.controller;

import cn.hutool.core.date.DateTime;
import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.listener.ChatEventListener;
import com.example.miniaibackend.mapper.MessageMapper;
import com.example.miniaibackend.models.AcceptDTO;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.listener.SseStreamListener;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatGptController {

    @Resource
    MessageMapper messageMapper;


    @PostMapping("/sse")
    @CrossOrigin
    public SseEmitter sendMsg(@RequestBody AcceptDTO acceptDTO) {
        Integer userId = acceptDTO.getUserId();
        int size = acceptDTO.getMessageList().size();
        com.plexpt.chatgpt.entity.chat.Message message = acceptDTO.getMessageList().get(size - 1);
        if (message.getRole().equals("user")) {
            String content = message.getContent();
            Integer conversationId = acceptDTO.getConversationId();
            DateTime userTime = new DateTime();
            Message userMessage = new Message(null, conversationId, userId, content, userTime);
            messageMapper.insert(userMessage);
        }
        SseEmitter sseEmitter = new SseEmitter(-1L);
        try {
            ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                    .apiKey(acceptDTO.getToken())
                    .apiHost(acceptDTO.getEndpoint())
                    .timeout(5000)
                    .build()
                    .init();
            ChatCompletion chatCompletion = ChatCompletion.builder()
                    //                       .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                    .model(acceptDTO.getModel())
                    .messages(acceptDTO.getMessageList())
                    .maxTokens(acceptDTO.getMaxToken())
                    .temperature(acceptDTO.getTemperature())
                    .presencePenalty(acceptDTO.getPresencePenalty())
                    .frequencyPenalty(acceptDTO.getFrequencyPenalty())
                    .topP(1)
                    .build();

            ChatEventListener listener = new ChatEventListener(sseEmitter);
            listener.setOnComplate(msg -> {
                String content = com.plexpt.chatgpt.entity.chat.Message.ofAssistant(msg).getContent();
                DateTime dateTime = new DateTime();
                Message botMessage = new Message(null, acceptDTO.getConversationId(), null, content, dateTime);
                messageMapper.insert(botMessage);
                return;
            });
            chatGPTStream.streamChatCompletion(chatCompletion, listener);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "===");
        }


        return sseEmitter;
    }

}
