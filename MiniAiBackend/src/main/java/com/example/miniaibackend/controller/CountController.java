package com.example.miniaibackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.miniaibackend.domain.Order;
import com.example.miniaibackend.mapper.MessageMapper;
import com.example.miniaibackend.mapper.OrderMapper;
import com.example.miniaibackend.mapper.UserMapper;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.service.MessageService;
import com.example.miniaibackend.service.OrderService;
import com.example.miniaibackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/count")
@CrossOrigin(origins = "*")
public class CountController {
    @Resource
    UserMapper userMapper;
    @Resource
    MessageMapper messageMapper;
    @Resource
    OrderMapper orderMapper;

    @PostMapping("/countInformation")
    public Result<?> getCountInformation() {
        Long allPeople = userMapper.selectCount(null);
        Long allMessage = messageMapper.selectCount(null);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct user_id");
        Long allPay = orderMapper.selectCount(queryWrapper);
        Double payPercent = (double)allPay / (double)allPeople;
        Map<String, Object> map = new HashMap<>();
        map.put("allPeople",allPeople);
        map.put("allMessage",allMessage);
        map.put("allPay",allPay);
        map.put("payPercent",payPercent);
        return Result.ok(map);
    }
}
