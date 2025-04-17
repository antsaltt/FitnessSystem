package com.groupI.fitness.service;

import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Cart;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface CartService {
    IPage<Cart> selectCartPage(QueryDTO queryDTO);
    IPage<Cart> selectCart(QueryDTO queryDTO);

    IPage<Cart> selectCartWithSameProductId(QueryDTO queryDTO);
    void batchDelete(List<Integer> ids);
    Integer addCart(Cart cart);

    Integer updateCart(Cart cart);

    Integer deleteCart(Integer id);


}
