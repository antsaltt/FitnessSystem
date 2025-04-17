package com.groupI.fitness.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Favorite;
import com.groupI.fitness.mapper.FavoriteMapper;
import com.groupI.fitness.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;


    @Override
    public Integer addFavorite(Favorite favorite) {

        List<Favorite> favoriteSelect = favoriteMapper.selectFavorite(new Page<>(1,100),favorite.getUserId().toString()).getRecords();
        for (Favorite tmp:favoriteSelect){
            if (Objects.equals(tmp.getDietId(),favorite.getDietId())){
                return favoriteMapper.updateById(tmp);
            }
        }
        return favoriteMapper.insert(favorite);
        //return -1;

    }



    @Override
    public Integer updateFavorite(Favorite favorite) {
        return favoriteMapper.updateById(favorite);
    }

    @Override
    public Integer deleteFavorite(Integer id) {
        return favoriteMapper.deleteById(id);
    }

    @Override
    public IPage<Favorite> selectFavoritePage(QueryDTO queryDTO) {
        Page<Favorite> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return favoriteMapper.selectFavoritePage(page,queryDTO.getKeyword());
    }

    @Override
    public IPage<Favorite> selectFavorite(QueryDTO queryDTO) {
        Page<Favorite> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return favoriteMapper.selectFavorite(page,queryDTO.getKeyword());
    }

    @Override
    public IPage<Favorite> selectFavoriteWithSameDietId(QueryDTO queryDTO) {
        Page<Favorite> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return favoriteMapper.selectFavoriteWithSameDietId(page,queryDTO.getKeyword());
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        favoriteMapper.deleteBatchIds(ids);
    }








}
