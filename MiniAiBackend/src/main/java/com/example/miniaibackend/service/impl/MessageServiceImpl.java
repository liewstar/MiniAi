package com.example.miniaibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.miniaibackend.domain.Message;
import com.example.miniaibackend.service.MessageService;
import com.example.miniaibackend.mapper.MessageMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 29354
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-11-01 08:53:20
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{
    @Resource
    MessageMapper messageMapper;

    @Override
    public List<Message> getMessage(Integer conversationId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("conversation_id", conversationId);
        return messageMapper.selectList(queryWrapper);
    }
}




