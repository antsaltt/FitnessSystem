package com.groupI.fitness.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Order;

import java.util.List;

public interface OrderService {



    Integer addOrder(Order order);

    Integer updateOrder(Order order);


    Integer deleteOrder(Integer id);

    void batchDelete(List<Integer> ids);
    IPage<Order> selectOrderPage(QueryDTO queryDTO);
    IPage<Order> selectOrderPageOneCustomer(QueryDTO queryDTO);
    IPage<Order> selectOrder(QueryDTO queryDTO);
}
