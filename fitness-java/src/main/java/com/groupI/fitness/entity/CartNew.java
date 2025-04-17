package com.groupI.fitness.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "cartnew")
public class CartNew {


    @TableId(type = IdType.AUTO)
    private Integer cartId;
    private Integer productId;
    private Integer productNum;
    private Integer userId;
    private String productName;
    private double productPrice;


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public CartNew() {
    }

    public CartNew(Integer cartId, Integer productId, Integer productNum, Integer userId, String productName,double productPrice) {
        this.cartId = cartId;
        this.productId = productId;
        this.productNum = productNum;
        this.userId = userId;
        this.productName = productName;
        this.productPrice = productPrice;
    }



    @Override
    public String toString() {
        return "CartNew{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", productNum=" + productNum +
                ", userId=" + userId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
