package com.example.miniaibackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.miniaibackend.domain.Conversation;
import com.example.miniaibackend.service.ConversationService;
import com.example.miniaibackend.mapper.ConversationMapper;
import org.springframework.stereotype.Service;

/**
* @author 29354
* @description 针对表【conversation】的数据库操作Service实现
* @createDate 2023-10-31 23:25:11
*/
@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation>
    implements ConversationService{

}




