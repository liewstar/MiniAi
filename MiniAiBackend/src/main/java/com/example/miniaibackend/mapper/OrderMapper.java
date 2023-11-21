package com.example.miniaibackend.mapper;

import com.example.miniaibackend.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.miniaibackend.models.OrderDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
* @author 29354
* @description 针对表【order】的数据库操作Mapper
* @createDate 2023-11-01 08:59:48
* @Entity com.example.miniaibackend.domain.Order
*/
public interface OrderMapper extends BaseMapper<Order> {


    List<OrderDTO> selectOrder(OrderDTO orderDTO);

}




