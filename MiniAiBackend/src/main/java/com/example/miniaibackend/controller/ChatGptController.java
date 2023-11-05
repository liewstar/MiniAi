package com.example.miniaibackend.controller;

import com.example.miniaibackend.models.AcceptDTO;
import com.example.miniaibackend.service.ChatGptService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatGptController {
    @Resource
    ChatGptService chatGptService;

    @PostMapping("/sendMsg")
    public ResponseEntity<?> sendMsg(@RequestBody AcceptDTO acceptDTO) {
        String Msg = chatGptService.Chat(acceptDTO);
        return ResponseEntity.ok(Msg);
    }
}
