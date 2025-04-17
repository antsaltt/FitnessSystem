package com.groupI.fitness.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.groupI.fitness.entity.Product;

public interface ProductMapper extends BaseMapper<Product> {
    IPage<Product> selectProductPage(Page<Product> page,String keyword);
    IPage<Product> selectProduct(Page<Product> page,String keyword);
    IPage<Product> selectProductById(Page<Product> page,String keyword);
}
