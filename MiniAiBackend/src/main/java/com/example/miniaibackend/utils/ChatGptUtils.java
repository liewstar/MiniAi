package com.example.miniaibackend.utils;

import com.example.miniaibackend.models.AcceptDTO;
import com.example.miniaibackend.models.Preset;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatGptUtils {
    public static String response(AcceptDTO acceptDTO) {
        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(acceptDTO.getToken())
                .apiHost(acceptDTO.getEndpoint())
                .build()
                .init();

        List<Message> messageList = new ArrayList<>();
        if(acceptDTO.getPresetList() != null) {
            for (Preset preset : acceptDTO.getPresetList()) {
                Message message;
                if (preset.getRole().equals("user")) {
                    message = Message.of(preset.getContent());
                }else if(preset.getRole().equals("system")) {
                    message = Message.ofSystem(preset.getContent());
                }else {
                    message = Message.ofAssistant(preset.getContent());
                }
                messageList.add(message);
            }
        }

        Message userMessage = Message.of(acceptDTO.getContent());
        messageList.add(userMessage);

        ChatCompletion chatCompletion = ChatCompletion.builder()
 //              .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .model(acceptDTO.getModel())
                .messages(messageList)
                .maxTokens(acceptDTO.getMaxToken())
                .temperature(acceptDTO.getTemperature())
                .presencePenalty(acceptDTO.getPresencePenalty())
                .frequencyPenalty(acceptDTO.getFrequencyPenalty())
                .build();
        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        return res.getContent();
    }
}
