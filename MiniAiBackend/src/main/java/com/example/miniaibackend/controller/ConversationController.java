package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Conversation;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.service.ConversationService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation")
@CrossOrigin(origins = "*")
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
    public Result<?> getConversation(Integer userId) {
        return Result.ok(conversationService.getConversation(userId)).setMessage("获取会话成功");
    }

    @PostMapping("/addConversation")
    public Result<?> addConversation(Integer userId, String title) {
        Conversation conversation = conversationService.addConversation(userId, title);
        return Result.ok(conversation).setMessage("添加会话成功");
    }

    @PostMapping("/changeConversation")
    public Result<?> changeConversation(Integer conversationId, String title) {
        return Result.ok(conversationService.changeConversation(conversationId, title));
    }
}
