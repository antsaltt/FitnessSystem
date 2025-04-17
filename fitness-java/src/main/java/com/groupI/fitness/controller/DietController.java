package com.groupI.fitness.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Diet;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.PlanService;
import com.groupI.fitness.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DietController {
    @Autowired
    private PlanService planService;

    @PostMapping("/api/user/diet/list")
    public Result dietList(@RequestBody QueryDTO queryDTO) {
        IPage<Diet> dietPage = planService.selectDietPage(queryDTO);
        List<Diet> dietSelect = dietPage.getRecords();
        long totalRecords = dietPage.getTotal(); // Get the number of total records
        Result result = new Result(200, "", dietSelect, totalRecords);   // Set total records to result
        result.setTotalRecords(totalRecords);
        return result;  // Return result with code, message, data, and total records
    }

    @PostMapping("/api/user/diet/detail/{dietName}")
    public Result dietDetail(@PathVariable String dietName) {
        List<Diet> dietSelect = planService.selectDietRough(dietName).getRecords();
        return new Result(200, "", dietSelect);
    }

    /**
     * Add
     *
     * @param diet
     * @return
     */
    @Autowired
    private DietService dietService;

    @PostMapping("/api/user/diet/add")
    public Result addDiet(@RequestBody Diet diet) {
        return new Result(200, "", dietService.addDiet(diet));
    }

    /**
     * Update
     *
     * @param diet
     * @return
     */
    @PostMapping("/api/user/diet/update")
    public Result updateUser(@RequestBody Diet diet) {
        return new Result(200, "", dietService.updateDiet(diet));
    }

    /**
     * Delete
     *
     * @param id
     * @return
     */
    @PostMapping("/api/user/diet/delete")
    public Result deleteUser(Integer id) {
        return new Result(200, "", dietService.deleteDiet(id));
    }

    /**
     * Batch delete
     *
     * @param ids
     * @return
     */
    @PostMapping("/api/user/diet/delete/batch")
    public Result batchDeleteDiet(@RequestBody List<Integer> ids) {
        dietService.batchDelete(ids);
        return new Result(200, "", "");
    }
}

