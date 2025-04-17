package com.groupI.fitness.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Lesson;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.CourseService;
import com.groupI.fitness.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class LessonController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/api/user/lesson/list")
    public Result LessonList(@RequestBody QueryDTO queryDTO) {
//        List<Lesson> lessonSelect = menuService.selectLessonPage(queryDTO).getRecords();
//        return new Result(200, "", lessonSelect);
        IPage<Lesson> lessonPage = courseService.selectLessonPage(queryDTO);
        List<Lesson> lessonSelect = lessonPage.getRecords();
        long totalRecords = lessonPage.getTotal(); // Get the number of total records
        Result result = new Result(200, "", lessonSelect, totalRecords);   // Set total records to result
        result.setTotalRecords(totalRecords);
        return result;  // Return result with code, message, data, and total records
    }

    @PostMapping("/api/user/lesson/detail/{lessonName}")
    public Result lessonDetail(@PathVariable String lessonName) {
        List<Lesson> lessonSelect = courseService.selectLessonRough(lessonName).getRecords();
        return new Result(200, "", lessonSelect);
    }

    /**
     * Add
     *
     * @param lesson
     * @return
     */
    @Autowired
    private LessonService lessonService;

    @PostMapping("/api/user/lesson/add")
    public Result addLesson(@RequestBody Lesson lesson) {
        return new Result(200, "", lessonService.addLesson(lesson));
    }

    /**
     * Update
     *
     * @param lesson
     * @return
     */
    @PostMapping("/api/user/lesson/update")
    public Result updateUser(@RequestBody Lesson lesson) {
        return new Result(200, "", lessonService.updateLesson(lesson));
    }

    /**
     * Delete
     *
     * @param id
     * @return
     */
    @PostMapping("/api/user/lesson/delete")
    public Result deleteUser(Integer id) {
        return new Result(200, "", lessonService.deleteLesson(id));
    }

    /**
     * Batch delete
     *
     * @param ids
     * @return
     */
    @PostMapping("/api/user/lesson/delete/batch")
    public Result batchDeleteLesson(@RequestBody List<Integer> ids) {
        lessonService.batchDelete(ids);
        return new Result(200, "", "");
    }
}
