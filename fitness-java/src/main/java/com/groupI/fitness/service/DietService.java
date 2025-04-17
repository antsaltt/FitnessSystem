package com.groupI.fitness.service;

import com.groupI.fitness.entity.Diet;

import java.util.List;

public interface DietService {



    Integer addDiet(Diet diet);

    Integer updateDiet(Diet diet);

    Integer deleteDiet(Integer id);

    void batchDelete(List<Integer> ids);

}

