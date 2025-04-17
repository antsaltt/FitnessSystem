package com.groupI.fitness.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.groupI.fitness.entity.Diet;

public interface DietMapper extends BaseMapper<Diet>{
    IPage<Diet> selectDietPage(Page<Diet> page,String keyword);
    IPage<Diet> selectDiet(Page<Diet> page,String keyword);
    IPage<Diet> selectDietById(Page<Diet> page,String keyword);
}
