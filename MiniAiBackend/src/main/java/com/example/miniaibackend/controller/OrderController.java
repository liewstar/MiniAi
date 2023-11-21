package com.example.miniaibackend.controller;


import com.example.miniaibackend.mapper.OrderMapper;
import com.example.miniaibackend.models.OrderArg;
import com.example.miniaibackend.models.OrderDTO;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.service.OrderService;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;

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
    @Resource
    OrderMapper orderMapper;

    //用户发送订单
    @PostMapping("/sendOrder")
    public Result<?> sendOrder(Integer userId, BigDecimal orderAmount, int days) {
        return Result.ok(orderService.sendOrder(userId,orderAmount,days));
    }

    //管理员查看订单
    @PostMapping("/selectOrder")
    public Result<?> selectOrder(@RequestBody OrderDTO orderDTO) {
        return Result.ok(orderService.selectOrder(orderDTO));
    }

    @PostMapping("/delete")
    public Result<?> deleteOrder(Integer orderId) {
        int i = orderMapper.deleteById(orderId);
        return Result.ok(i);
    }



}
