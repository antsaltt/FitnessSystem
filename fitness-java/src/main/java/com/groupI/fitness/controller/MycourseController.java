package com.groupI.fitness.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Mycourse;
import com.groupI.fitness.entity.MycourseNew;
import com.groupI.fitness.entity.Lesson;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.CourseService;
import com.groupI.fitness.service.MycourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MycourseController {
    @Autowired
    private MycourseService mycourseService;

    @Autowired
    private CourseService courseService;

    /**
     * 分页查询
     * @param queryDTO
     * @return
     */

    @PostMapping("/api/mycourseNew/list")
    public Result mycourseNewList(@RequestBody QueryDTO queryDTO){


        IPage<Mycourse> mycoursePage = mycourseService.selectMycoursePage(queryDTO);
        List<Mycourse> mycourseSelect = mycoursePage.getRecords();
        long totalRecords = mycoursePage.getTotal(); // Get the number of total records
        List<MycourseNew> mycourseNewSelect = new ArrayList<>();

        for (int i = 0; i < mycourseSelect.size(); i++) {
            QueryDTO queryLessonDTO = new QueryDTO();
            queryLessonDTO.setPageNo(1);
            queryLessonDTO.setPageSize(1000);
            String lessonId = String.valueOf(mycourseSelect.get(i).getLessonId());
            queryLessonDTO.setKeyword(lessonId);


            IPage<Lesson> lessonPage = courseService.selectLessonById(queryLessonDTO);
            List<Lesson> lessonSelect = lessonPage.getRecords();
            if (lessonSelect.size() == 0) {
                continue;
            }
            Lesson lesson = lessonSelect.get(0);

            // Add new mycourse item to mycourseNewSelect
            mycourseNewSelect.add(new MycourseNew(mycourseSelect.get(i).getMycourseId(),
                    mycourseSelect.get(i).getLessonId(),
                    mycourseSelect.get(i).getLessonName(),
                    mycourseSelect.get(i).getLessonInstructor(),
                    mycourseSelect.get(i).getLessonTime(),
                    mycourseSelect.get(i).getUserId()
            ) );
        }
        Result result = new Result(200, "", mycourseNewSelect, totalRecords);
        return result;


    }


    @PostMapping("/api/mycourse/mycourseNumber/{userId}")
    public Result mycourseNumber(@PathVariable Integer userId){
        QueryDTO queryDTO=new QueryDTO();
        queryDTO.setPageNo(1);
        queryDTO.setPageSize(1000);
        queryDTO.setKeyword(userId.toString());
        IPage<Mycourse> mycoursePage = mycourseService.selectMycoursePage(queryDTO);
        long totalRecords = mycoursePage.getTotal(); // Get the number of total records
        return new Result(200, "",totalRecords);  // Return result with code, message, data, and total records
    }

    /**
     * Add
     * @param mycourse
     * @return
     */
    @PostMapping("/api/mycourse/add")
    public Result addMycourse(@RequestBody Mycourse mycourse){
        return new Result(200,"",mycourseService.addMycourse(mycourse));
    }

    /**
     * Update
     * @param mycourse
     * @return
     */
    @PostMapping("/api/mycourse/update")
    public Result updateMycourse(@RequestBody Mycourse mycourse){
        return new Result(200,"",mycourseService.updateMycourse(mycourse));
    }

    /**
     * Delete
     * @param id
     * @return
     */
    @PostMapping("/api/mycourse/delete")
    public Result deleteMycourse(Integer id){
        return new Result(200,"",mycourseService.deleteMycourse(id));
    }

    /**
     * Batch delete
     * @param ids
     * @return
     */
    @PostMapping("/api/mycourse/delete/batch")
    public Result batchDeleteMycourse(@RequestBody List<Integer> ids){
        mycourseService.batchDelete(ids);
        return new Result(200,"","");
    }
}
