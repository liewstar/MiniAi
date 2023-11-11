package com.example.miniaibackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.miniaibackend.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 29354
* @description 针对表【order】的数据库操作Service
* @createDate 2023-11-01 08:59:48
*/
public interface OrderService extends IService<Order> {
    Order sendOrder(Integer userId, BigDecimal orderAmount, int days);

    IPage<Order> selectOrder(Map<String, Object> map,int pageNum, int pageSize);



}
