package com.groupI.fitness.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Diet;
import com.groupI.fitness.mapper.DietMapper;
import com.groupI.fitness.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private DietMapper dietMapper;
    @Override
    public IPage<Diet> selectDietPage(QueryDTO queryDTO) {
        Page<Diet> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return dietMapper.selectDietPage(page,queryDTO.getKeyword());
    }

    @Override
    public IPage<Diet> selectDiet(String dietname) {
        Page<Diet> page=new Page<>(1,1);
        return dietMapper.selectDiet(page,dietname);
    }

    @Override
    public IPage<Diet> selectDietById(QueryDTO queryDTO) {
        Page<Diet> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return dietMapper.selectDietById(page,queryDTO.getKeyword());
    }

    @Override
    public IPage<Diet> selectDietRough(String dietname) {
        Page<Diet> page=new Page<>(1,100);
        return dietMapper.selectDiet(page,dietname);
    }

}
