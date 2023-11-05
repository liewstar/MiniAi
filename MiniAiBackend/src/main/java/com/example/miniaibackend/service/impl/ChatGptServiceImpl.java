package com.example.miniaibackend.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.miniaibackend.domain.Conversation;
import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.mapper.ConversationMapper;
import com.example.miniaibackend.mapper.MessageMapper;
import com.example.miniaibackend.models.AcceptDTO;
import com.example.miniaibackend.service.ChatGptService;
import com.example.miniaibackend.utils.ChatGptUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
public class ChatGptServiceImpl implements ChatGptService {
    @Resource
    ConversationMapper conversationMapper;

    @Resource
    MessageMapper messageMapper;


    //用户发起消息
    @Override
    public String Chat(AcceptDTO acceptDTO) {
        Integer userId = acceptDTO.getUserId();
        String content = acceptDTO.getContent();
        Integer conversationId = acceptDTO.getConversationId();

        DateTime userTime = new DateTime();
        Message userMessage = new Message(null, conversationId, userId, content, userTime);
        messageMapper.insert(userMessage);

        String response = ChatGptUtils.response(acceptDTO);
        DateTime botTime = new DateTime();
        Message botMessage = new Message(null, conversationId,null,content,botTime);
        messageMapper.insert(botMessage);
        return response;
    }
}
