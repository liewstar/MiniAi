package com.example.miniaibackend.service;

import com.example.miniaibackend.domain.Conversation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 29354
* @description 针对表【conversation】的数据库操作Service
* @createDate 2023-10-31 23:25:11
*/
public interface ConversationService extends IService<Conversation> {
    List<Conversation> getConversation(Integer id);

    Conversation addConversation(Integer userId, String title);

    Conversation changeConversation(Integer conversationId, String title);
}
