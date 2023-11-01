package com.example.miniaibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.miniaibackend.domain.Order;
import com.example.miniaibackend.service.OrderService;
import com.example.miniaibackend.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String sendOrder(Integer userId, BigDecimal orderAmount, int days) {
        //查询userId最大的order_date是否存在，存在则判断下单日期是否在有效期内，在则延长，不在则新增
        //不存在则直接新增
        return null;

    }

    @Override
    public List<Order> selectOrder(Map<String, Object> map) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value != null) {
                queryWrapper.eq(key, value);
            }
        }
        return orderMapper.selectList(queryWrapper);
    }

}




