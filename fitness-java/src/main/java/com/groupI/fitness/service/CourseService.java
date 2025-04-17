package com.groupI.fitness.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Lesson;



public interface CourseService {
    IPage<Lesson> selectLessonPage(QueryDTO queryDTO);
    IPage<Lesson> selectLesson(String lessonname);

    IPage<Lesson> selectLessonById(QueryDTO queryDTO);
    IPage<Lesson> selectLessonRough(String lessonname);


}
