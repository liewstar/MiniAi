package com.example.miniaibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.miniaibackend.domain.Order;
import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.service.OrderService;
import com.example.miniaibackend.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
* @author 29354
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-11-01 08:59:48
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Resource
    OrderMapper orderMapper;

    @Override
    public Order sendOrder(Integer userId, BigDecimal orderAmount, int days) {
        //都是新增订单，但是需要判断用户此时是否在会员期内，在则延长之前的日期新增，不在则直接新增
        QueryWrapper<Order> queryWrapperExist = new QueryWrapper<>();
        Date now = new Date();
        queryWrapperExist.eq("user_id",userId)
                .ge("end_date",now)
                .orderByDesc("id")
                .last("LIMIT 1");
        Order order = orderMapper.selectOne(queryWrapperExist);
        Calendar calendar = Calendar.getInstance();
        Date startDate = null;
        if(order == null) {
            //直接新增
            startDate = new Date();
            calendar.setTime(new Date());
        }else {
            //新增延长
            startDate = order.getEndDate();
            calendar.setTime(startDate);
        }
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Order newOrder = new Order(null,userId,new Date(),orderAmount,startDate,calendar.getTime());
        orderMapper.insert(newOrder);
        return newOrder;
    }

    @Override
    public IPage<Order> selectOrder(Map<String, Object> map,int pageNum, int pageSize) {
        System.out.println(pageNum);
        System.out.println(pageSize);
        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                queryWrapper.eq(key, value);
            }
        }
        return orderMapper.selectPage(page, queryWrapper);
    }



}




