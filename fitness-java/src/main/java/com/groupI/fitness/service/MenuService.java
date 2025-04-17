package com.groupI.fitness.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Product;

public interface MenuService {
    IPage<Product> selectProductPage(QueryDTO queryDTO);
    IPage<Product> selectProduct(String productname);

    IPage<Product> selectProductById(QueryDTO queryDTO);
    IPage<Product> selectProductRough(String productname);
}
