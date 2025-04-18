package com.groupI.fitness.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Product;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.MenuService;
import com.groupI.fitness.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/api/user/product/list")
    public Result productList(@RequestBody QueryDTO queryDTO) {
        IPage<Product> productPage = menuService.selectProductPage(queryDTO);
        List<Product> productSelect = productPage.getRecords();
        long totalRecords = productPage.getTotal(); // Get the number of total records
        Result result = new Result(200, "", productSelect, totalRecords);   // Set total records to result
        result.setTotalRecords(totalRecords);
        return result;   // Return result with code, message, data, and total records
    }

    @PostMapping("/api/user/product/detail/{productName}")
    public Result productDetail(@PathVariable String productName) {
        List<Product> productSelect = menuService.selectProductRough(productName).getRecords();
        return new Result(200, "", productSelect);
    }

    /**
     * Add
     *
     * @param product
     * @return
     */
    @Autowired
    private ProductService productService;

    @PostMapping("/api/user/product/add")
    public Result addProduct(@RequestBody Product product) {
        return new Result(200, "", productService.addProduct(product));
    }

    /**
     * Update
     *
     * @param product
     * @return
     */
    @PostMapping("/api/user/product/update")
    public Result updateUser(@RequestBody Product product) {
        return new Result(200, "", productService.updateProduct(product));
    }

    /**
     * Delete
     *
     * @param id
     * @return
     */
    @PostMapping("/api/user/product/delete")
    public Result deleteUser(Integer id) {
        return new Result(200, "", productService.deleteProduct(id));
    }

    /**
     * Batch delete
     *
     * @param ids
     * @return
     */
    @PostMapping("/api/user/product/delete/batch")
    public Result batchDeleteProduct(@RequestBody List<Integer> ids) {
        productService.batchDelete(ids);
        return new Result(200, "", "");
    }
}

