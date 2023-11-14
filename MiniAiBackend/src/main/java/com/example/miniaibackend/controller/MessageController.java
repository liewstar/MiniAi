package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "*")
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

    //管理员查看消息统计
    @PostMapping("/statistics")
    public Result<?> selectStatistics(String date) throws ParseException {
        return Result.ok(messageService.statisticsByDay(date));
    }
}
