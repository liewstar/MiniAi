package com.example.miniaibackend.models;

import com.plexpt.chatgpt.entity.chat.Message;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AcceptDTO {

    private Integer userId;

    private Integer conversationId;


    private String token;


    private String endpoint;


    private String model;


    private int maxToken;


    private double temperature;


    private double presencePenalty;


    private double frequencyPenalty;


    private List<Message> messageList;
}
