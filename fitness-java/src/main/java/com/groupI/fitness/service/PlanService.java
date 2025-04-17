package com.groupI.fitness.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Diet;

public interface PlanService {
    IPage<Diet> selectDietPage(QueryDTO queryDTO);
    IPage<Diet> selectDiet(String dietname);

    IPage<Diet> selectDietById(QueryDTO queryDTO);
    IPage<Diet> selectDietRough(String dietname);
}
