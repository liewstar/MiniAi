package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Conversation;
import com.example.miniaibackend.service.ConversationService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conversation")
/*
* 用户新建会话
* 用户删除自己的会话
* 用户查看自己的会话
* 管理员查看会话
* */
public class ConversationController {

    @Resource
    ConversationService conversationService;

    @PostMapping("/getConversation")
    public ResponseEntity<List<Conversation>> getConversation(Integer id) {
        return ResponseEntity.ok(conversationService.getConversation(id));
    }
}
