package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.Order;
import com.example.miniaibackend.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/order")
/*
* 用户新增订单
* 管理员查看订单
* 用户查看订单到期时间
* */
public class OrderController {
    @Resource
    OrderService orderService;

    @PostMapping("/sendOrder")
    public ResponseEntity<?> sendOrder(Integer userId, BigDecimal orderAmount, int days) {
        return null;
    }

    @PostMapping("/selectOrder")
    public ResponseEntity<List<Order>> selectOrder(HashMap<String, Object> map) {
        return ResponseEntity.ok(orderService.selectOrder(map));
    }


}
