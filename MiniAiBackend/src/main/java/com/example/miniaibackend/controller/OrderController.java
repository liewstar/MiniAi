package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Order;
import com.example.miniaibackend.models.OrderArg;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Result<?> selectOrder(@RequestBody OrderArg orderArg) {
        System.out.println(orderArg);
        return Result.ok(orderService.selectOrder(orderArg.getMap(),orderArg.getPageNum(),orderArg.getPageSize()));
    }

    //管理员查看订单统计



}
