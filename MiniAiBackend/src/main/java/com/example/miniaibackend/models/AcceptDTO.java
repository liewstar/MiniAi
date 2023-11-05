package com.example.miniaibackend.models;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class AcceptDTO {

    private Integer userId;

    private Integer conversationId;


    private String token;


    private String endpoint;

    private String content;

    private String model;


    private int maxToken;


    private float temperature;


    private float presencePenalty;


    private float frequencyPenalty;


    private List<Preset> presetList;
}
