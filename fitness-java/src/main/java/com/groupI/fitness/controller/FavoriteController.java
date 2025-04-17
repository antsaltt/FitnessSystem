package com.groupI.fitness.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Diet;
import com.groupI.fitness.entity.Favorite;
import com.groupI.fitness.entity.FavoriteNew;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.FavoriteService;
import com.groupI.fitness.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private PlanService planService;

    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
    @PostMapping("/api/favorite/list")
    public Result favoriteList(@RequestBody QueryDTO queryDTO){
        IPage<Favorite> favoritePage = favoriteService.selectFavoritePage(queryDTO);
        List<Favorite> favoriteSelect = favoritePage.getRecords();
        long totalRecords = favoritePage.getTotal(); // Get the number of total records
        Result result = new Result(200, "", favoriteSelect, totalRecords);   // Set total records to result
        return result;  // Return result with code, message, data, and total records
    }

    @PostMapping("/api/favoriteNew/list")
    public Result favoriteNewList(@RequestBody QueryDTO queryDTO){


        IPage<Favorite> favoritePage = favoriteService.selectFavoritePage(queryDTO);
        List<Favorite> favoriteSelect = favoritePage.getRecords();
        long totalRecords = favoritePage.getTotal(); // Get the number of total records
        List<FavoriteNew> favoriteNewSelect = new ArrayList<>();

        for (int i = 0; i < favoriteSelect.size(); i++) {
            QueryDTO queryDietDTO = new QueryDTO();
            queryDietDTO.setPageNo(1);
            queryDietDTO.setPageSize(1000);
            String dietId = String.valueOf(favoriteSelect.get(i).getDietId());
            queryDietDTO.setKeyword(dietId);


            IPage<Diet> dietPage = planService.selectDietById(queryDietDTO);
            List<Diet> dietSelect = dietPage.getRecords();
            if (dietSelect.size() == 0) {
                continue;
            }
            Diet diet = dietSelect.get(0);

            // Add new favorite item to favoriteNewSelect
            favoriteNewSelect.add(new FavoriteNew(favoriteSelect.get(i).getFavoriteId(),
                    favoriteSelect.get(i).getDietId(),
                    favoriteSelect.get(i).getUserId(),
                    diet.getDietName()));
        }
        Result result = new Result(200, "", favoriteNewSelect, totalRecords);
        return result;


    }


    @PostMapping("/api/favorite/favoriteNumber/{userId}")
    public Result favoriteNumber(@PathVariable Integer userId){
        QueryDTO queryDTO=new QueryDTO();
        queryDTO.setPageNo(1);
        queryDTO.setPageSize(1000);
        queryDTO.setKeyword(userId.toString());
        IPage<Favorite> favoritePage = favoriteService.selectFavoritePage(queryDTO);
        long totalRecords = favoritePage.getTotal(); // Get the number of total records
        return new Result(200, "",totalRecords);  // Return result with code, message, data, and total records
    }

    /**
     * Add
     * @param favorite
     * @return
     */
    @PostMapping("/api/favorite/add")
    public Result addFavorite(@RequestBody Favorite favorite){
        return new Result(200,"",favoriteService.addFavorite(favorite));
    }

    /**
     * Update
     * @param favorite
     * @return
     */
    @PostMapping("/api/favorite/update")
    public Result updateFavorite(@RequestBody Favorite favorite){
        return new Result(200,"",favoriteService.updateFavorite(favorite));
    }

    /**
     * Delete
     * @param id
     * @return
     */
    @PostMapping("/api/favorite/delete")
    public Result deleteFavorite(Integer id){
        return new Result(200,"",favoriteService.deleteFavorite(id));
    }

    /**
     * Batch delete
     * @param ids
     * @return
     */
    @PostMapping("/api/favorite/delete/batch")
    public Result batchDeleteFavorite(@RequestBody List<Integer> ids){
        favoriteService.batchDelete(ids);
        return new Result(200,"","");
    }

}
