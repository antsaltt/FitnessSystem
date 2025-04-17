package com.groupI.fitness.controller;


import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Product;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/api/menu/{category}")
    public Result ShowMenu(@PathVariable String category){
        QueryDTO queryDTO=new QueryDTO();
        queryDTO.setPageNo(1);
        queryDTO.setPageSize(100);
        queryDTO.setKeyword(category);
        if (Objects.equals(category, "all")){queryDTO.setKeyword("");}
        return new Result(200,"",menuService.selectProductPage(queryDTO));
    }
    @PostMapping("/api/menu/product/{productName}")
    public Result ShowProduct(@PathVariable String productName){

        Product productSelect=menuService.selectProduct(productName).getRecords().get(0);

        return new Result(200,"",productSelect);
    }
}
