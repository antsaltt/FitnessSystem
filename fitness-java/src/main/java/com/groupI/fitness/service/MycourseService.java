package com.groupI.fitness.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Mycourse;

import java.util.List;

public interface MycourseService {
    IPage<Mycourse> selectMycoursePage(QueryDTO queryDTO);
    IPage<Mycourse> selectMycourse(QueryDTO queryDTO);

    IPage<Mycourse> selectMycourseWithSameLessonId(QueryDTO queryDTO);
    void batchDelete(List<Integer> ids);
    Integer addMycourse(Mycourse mycourse);

    Integer updateMycourse(Mycourse mycourse);

    Integer deleteMycourse(Integer id);

//    IPage<Lesson> selectLessonById(QueryDTO queryLessonDTO);
}
