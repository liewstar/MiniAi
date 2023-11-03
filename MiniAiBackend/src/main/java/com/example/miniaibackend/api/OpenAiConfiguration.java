package com.example.miniaibackend.api;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

public class OpenAiConfiguration {

    @Value("${open.ai.key}")
    private String openAiKey;

    @Value("${open.ai.request.timeout}")
    private long timeOut;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openAiKey, Duration.ofSeconds(timeOut));
    }
}
