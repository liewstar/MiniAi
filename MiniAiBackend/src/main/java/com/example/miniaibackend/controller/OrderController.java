package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Order;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
/*
* 用户新增订单
* 管理员查看订单
* 用户查看订单到期时间
* */
public class OrderController {
    @Resource
    OrderService orderService;

    //用户发送订单
    @PostMapping("/sendOrder")
    public Result<?> sendOrder(Integer userId, BigDecimal orderAmount, int days) {
        return Result.ok(orderService.sendOrder(userId,orderAmount,days));
    }

    //管理员查看订单
    @PostMapping("/selectOrder")
    public Result<?> selectOrder(HashMap<String, Object> map,int pageNum, int pageSize) {
        return Result.ok(orderService.selectOrder(map,pageNum,pageSize));
    }

    //管理员查看订单统计



}
