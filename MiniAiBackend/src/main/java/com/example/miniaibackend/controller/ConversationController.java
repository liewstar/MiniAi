package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Conversation;
import com.example.miniaibackend.service.ConversationService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation")
@CrossOrigin
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
    public ResponseEntity<List<Conversation>> getConversation(Integer userId) {
        return ResponseEntity.ok(conversationService.getConversation(userId));
    }

    @PostMapping("/addConversation")
    public ResponseEntity<?> addConversation(Integer userId, String title) {
        conversationService.addConversation(userId, title);
        return ResponseEntity.ok().body("success");
    }
}
