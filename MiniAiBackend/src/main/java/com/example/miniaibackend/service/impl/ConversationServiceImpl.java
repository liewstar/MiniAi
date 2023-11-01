package com.example.miniaibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.miniaibackend.domain.Conversation;
import com.example.miniaibackend.service.ConversationService;
import com.example.miniaibackend.mapper.ConversationMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 29354
* @description 针对表【conversation】的数据库操作Service实现
* @createDate 2023-10-31 23:25:11
*/
@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation>
    implements ConversationService{

    @Resource
    ConversationMapper conversationMapper;

    @Override
    public List<Conversation> getConversation(Integer id) {
        QueryWrapper<Conversation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        List<Conversation> conversations = conversationMapper.selectList(queryWrapper);
        return conversations;
    }
}




