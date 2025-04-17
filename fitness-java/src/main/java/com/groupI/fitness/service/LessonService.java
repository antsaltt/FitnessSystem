package com.groupI.fitness.service;

import com.groupI.fitness.entity.Lesson;

import java.util.List;

public interface LessonService {



    Integer addLesson(Lesson lesson);

    Integer updateLesson(Lesson lesson);

    Integer deleteLesson(Integer id);

    void batchDelete(List<Integer> ids);

}
