package com.groupI.fitness.service.impl;

import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Cart;
import com.groupI.fitness.mapper.CartMapper;
import com.groupI.fitness.service.CartService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public Integer addCart(Cart cart) {
//        if (cart1==null){return cartMapper.insert(cart);}
//        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id", cart.getCartId());
//        Cart cart1=cartMapper.selectOne(wrapper);

        List<Cart> cartSelect = cartMapper.selectCart(new Page<>(1,100),cart.getUserId().toString()).getRecords();
            // if (Objects.equals(cart1.getProductId(), cart.getProductId())&&Objects.equals(cart1.getProductSize(), cart.getProductId()))
           for (Cart tmp:cartSelect){
               if (Objects.equals(tmp.getProductId(),cart.getProductId())){
                   tmp.setProductNum(tmp.getProductNum()+cart.getProductNum());
                   return cartMapper.updateById(tmp);
               }
           }
        return cartMapper.insert(cart);
            //return -1;

    }

    @Override
    public Integer updateCart(Cart cart) {
        return cartMapper.updateById(cart);
    }
    @Override
    public Integer deleteCart(Integer id) {
        return cartMapper.deleteById(id);
    }
    @Override
    public void batchDelete(List<Integer> ids) {
        cartMapper.deleteBatchIds(ids);
    }
    @Override
    public IPage<Cart> selectCartPage(QueryDTO queryDTO) {
        Page<Cart> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return cartMapper.selectCartPage(page,queryDTO.getKeyword());
    }
    @Override
    public IPage<Cart> selectCart(QueryDTO queryDTO) {
        Page<Cart> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return cartMapper.selectCart(page,queryDTO.getKeyword());
    }

    @Override
    public IPage<Cart> selectCartWithSameProductId(QueryDTO queryDTO) {
        Page<Cart> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return cartMapper.selectCartWithSameProductId(page,queryDTO.getKeyword());
    }

}
