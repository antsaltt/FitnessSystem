package com.groupI.fitness.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.*;
import com.groupI.fitness.mapper.CartMapper;
import com.groupI.fitness.mapper.OrderMapper;
import com.groupI.fitness.mapper.ProductMapper;
import com.groupI.fitness.mapper.UserMapper;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;


    // Get all orders for staff
    @PostMapping("/api/user/order/list")
    public Result orderList(@RequestBody QueryDTO queryDTO) {
        IPage<Order> orderPage = orderService.selectOrderPage(queryDTO);
        List<Order> orderSelect = orderPage.getRecords();
        long totalRecords = orderPage.getTotal(); // Get the number of total records
        List<OrderFragmentToFront> orderFragmentToFronts=convertToFront(orderSelect);
        return new Result(200, "", orderFragmentToFronts, totalRecords);
    }
    // Get all orders for customer
    @PostMapping("/api/order/list")
    public Result orderOneCustomer(@RequestBody QueryDTO queryDTO) {
        IPage<Order> orderPage = orderService.selectOrderPageOneCustomer(queryDTO);
        List<Order> orderSelect = orderPage.getRecords();
        long totalRecords = orderPage.getTotal(); // Get the number of total records
        List<OrderFragmentToFront> orderFragmentToFronts=convertToFront(orderSelect);
        return new Result(200, "", orderFragmentToFronts, totalRecords);
    }
    // Get order detail
    @PostMapping("/api/order/list/detail")
    public Result orderDetail(@RequestBody QueryDTO queryDTO) {
        //QueryDTO queryDTO=new QueryDTO(1,10,orderId);
        IPage<Order> orderPage = orderService.selectOrder(queryDTO);
        List<Order> orderSelect = orderPage.getRecords();
        OrderFragmentToFront orderFragmentToFronts=convertToFront(orderSelect).get(0);
        return new Result(200, "", orderFragmentToFronts);
    }


    /**
     * Add
     */

    @PostMapping("/api/order/add")
    public Result addOrder(@RequestBody OrderFragmentFromFront orderFragmentFromFront) {
        List<Integer> ids= orderFragmentFromFront.getCartIds();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// Set time format
       // sdf.applyPattern();
        Date date = new Date();// Get system time
        Order order = new Order();
        order.setOrderCustomerId(orderFragmentFromFront.getUserId());
        order.setOrderComment(orderFragmentFromFront.getOrderComment());
        order.setOrderTime(sdf.format(date));
        order.setOrderAddress(orderFragmentFromFront.getOrderAddress());
        order.setOrderStatus("In Process");

        StringBuilder orderInfoName= new StringBuilder();
        StringBuilder orderInfoSize=new StringBuilder();
        StringBuilder orderInfoNumber=new StringBuilder();
        StringBuilder orderInfoPrice=new StringBuilder();
        int count=1;
        double orderPrice=0;
        for (Integer i:ids){
            Cart cart=cartMapper.selectById(i);
            Integer productid=cart.getProductId();
            Double price=productMapper.selectById(productid).getProductPrice();
            orderInfoName.append(cart.getProductName());
            orderInfoNumber.append(cart.getProductNum());
            orderInfoPrice.append(price.toString());
            orderPrice+=price*cart.getProductNum();
            if (count!=ids.size()){
                count++;
                orderInfoName.append(",");
                orderInfoSize.append(",");
                orderInfoNumber.append(",");
                orderInfoPrice.append(",");
            }

        }

        order.setOrderPrice(orderPrice);
        order.setOrderInfoName(String.valueOf(orderInfoName));
        order.setOrderInfoNumber(String.valueOf(orderInfoNumber));
        order.setOrderInfoPrice(String.valueOf(orderInfoPrice));
        User user=userMapper.selectById(orderFragmentFromFront.getUserId());
        Double money=user.getMoney();
        money-=orderPrice;
        if (money<0){

            return new Result(400, "Insufficient balance", -1);
        }
        else {

            user.setMoney(money);
            cartMapper.deleteBatchIds(ids);
            userMapper.updateById(user);
        return new Result(200, "", orderService.addOrder(order));}

    }

//    /**
//     * Update
//     *
//     * @param product
//     * @return
//     */
    @PostMapping("/api/order/update")
    public Result updateOrder(@RequestBody Order order) {
        Order order1=orderMapper.selectById(order.getOrderId());
        if (order1.getOrderStatus().equals("In Process")){
        if (!order.getOrderAddress().equals("null")){order1.setOrderAddress(order.getOrderAddress());}
        if (!order.getOrderComment().equals("null")){order1.setOrderComment(order.getOrderComment());}}

        if (!order.getOrderStatus().equals("null")){order1.setOrderStatus(order.getOrderStatus());}
        return new Result(200, "", orderService.updateOrder(order1));
    }
//
//    /**
//     * Delete
//     *
//     * @param id
//     * @return
//     */
    @PostMapping("/api/order/delete")
    public Result deleteOrder(Integer id) {
        return new Result(200, "", orderService.deleteOrder(2));
    }
//
//    /**
//     * Batch delete
//     *
//     * @param ids
//     * @return
//     */
    @PostMapping("/api/order/delete/batch")
    public Result batchDeleteProduct(@RequestBody List<Integer> ids) {
        orderService.batchDelete(ids);
        return new Result(200, "", "");
    }
    public List<OrderFragmentToFront> convertToFront(List<Order> orders){
if (orders.isEmpty()){
    return null;
}
        List<OrderFragmentToFront> convertedList= new ArrayList<>();

        for (Order orderTmp: orders){
            OrderFragmentToFront orderFragmentToFront=new OrderFragmentToFront();
            List<OrderInfo> orderInfos= new ArrayList<>();
            String[] splitInfoName=orderTmp.getOrderInfoName().split(",");
            String[] splitInfoNumber=orderTmp.getOrderInfoNumber().split(",");
            String[] splitInfoPrice=orderTmp.getOrderInfoPrice().split(",");
            int count=0;
            while (count!=splitInfoPrice.length){
                OrderInfo orderInfo=new OrderInfo();
                orderInfo.setName(splitInfoName[count]);
                orderInfo.setNumber(Integer.parseInt(splitInfoNumber[count]));
                orderInfo.setPrice(Double.parseDouble(splitInfoPrice[count]));
                orderInfos.add(orderInfo);
                count++;
            }
            orderFragmentToFront.setOrderInfos(orderInfos);
            orderFragmentToFront.setOrderId(orderTmp.getOrderId());
            orderFragmentToFront.setOrderCustomerId(orderTmp.getOrderCustomerId());
            orderFragmentToFront.setOrderPrice(orderTmp.getOrderPrice());
            orderFragmentToFront.setOrderComment(orderTmp.getOrderComment());
            orderFragmentToFront.setOrderTime(orderTmp.getOrderTime());
            orderFragmentToFront.setOrderAddress(orderTmp.getOrderAddress());
            orderFragmentToFront.setOrderStatus(orderTmp.getOrderStatus());
            convertedList.add(orderFragmentToFront);
        }
        return convertedList;
    }
}

