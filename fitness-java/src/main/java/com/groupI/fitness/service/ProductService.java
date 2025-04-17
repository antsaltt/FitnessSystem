package com.groupI.fitness.service;

import com.groupI.fitness.entity.Product;

import java.util.List;

public interface ProductService {



    Integer addProduct(Product product);

    Integer updateProduct(Product product);

    Integer deleteProduct(Integer id);

    void batchDelete(List<Integer> ids);

}
