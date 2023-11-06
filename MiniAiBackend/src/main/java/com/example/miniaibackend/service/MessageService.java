package com.example.miniaibackend.service;

import com.example.miniaibackend.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 29354
* @description 针对表【message】的数据库操作Service
* @createDate 2023-11-01 08:53:20
*/
public interface MessageService extends IService<Message> {
    List<Message> getMessage(Integer conversationId);

}
