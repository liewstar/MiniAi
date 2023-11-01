package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    MessageService messageService;

    @PostMapping("/getMessage")
    public ResponseEntity<List<Message>> getMessage(Integer userId) {
        return ResponseEntity.ok(messageService.getMessage(userId));
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(Integer userId, String content, Integer conversationId) {
        return null;
    }
}
