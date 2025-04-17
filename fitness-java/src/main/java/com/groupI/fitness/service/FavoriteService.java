package com.groupI.fitness.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    IPage<Favorite> selectFavoritePage(QueryDTO queryDTO);
    IPage<Favorite> selectFavorite(QueryDTO queryDTO);

    IPage<Favorite> selectFavoriteWithSameDietId(QueryDTO queryDTO);
    void batchDelete(List<Integer> ids);
    Integer addFavorite(Favorite favorite);

    Integer updateFavorite(Favorite favorite);

    Integer deleteFavorite(Integer id);
}
