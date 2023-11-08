package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin
/*
* 用户新建消息
* 用户查看自己的消息
* 管理员查看消息
* */
public class MessageController {
    @Resource
    MessageService messageService;

    @PostMapping("/getMessage")
    public Result<?> getMessage(Integer conversationId) {
        //return ResponseEntity.ok(messageService.getMessage(conversationId));
        return Result.ok(messageService.getMessage(conversationId)).setMessage("获取聊天记录成功");
    }
}
