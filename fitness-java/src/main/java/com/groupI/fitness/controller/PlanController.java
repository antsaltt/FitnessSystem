package com.groupI.fitness.controller;

import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Diet;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PlanController {
    @Autowired
    private PlanService planService;

    @PostMapping("/api/plan/{category}")
    public Result ShowPlan(@PathVariable String category){
        QueryDTO queryDTO=new QueryDTO();
        queryDTO.setPageNo(1);
        queryDTO.setPageSize(100);
        queryDTO.setKeyword(category);
        if (Objects.equals(category, "all")){queryDTO.setKeyword("");}
        return new Result(200,"",planService.selectDietPage(queryDTO));
    }
    @PostMapping("/api/plan/diet/{dietName}")
    public Result ShowDiet(@PathVariable String dietName){

        Diet dietSelect=planService.selectDiet(dietName).getRecords().get(0);

        return new Result(200,"",dietSelect);
    }
}
