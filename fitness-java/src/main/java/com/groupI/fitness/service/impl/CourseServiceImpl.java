package com.groupI.fitness.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Lesson;
import com.groupI.fitness.mapper.LessonMapper;
import com.groupI.fitness.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public IPage<Lesson> selectLessonPage(QueryDTO queryDTO) {
        Page<Lesson> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return lessonMapper.selectLessonPage(page,queryDTO.getKeyword());
    }
    @Override
    public IPage<Lesson> selectLesson(String lessonname) {
        Page<Lesson> page=new Page<>(1,1);
        return lessonMapper.selectLesson(page,lessonname);
    }

    @Override
    public IPage<Lesson> selectLessonRough(String lessonname) {
        Page<Lesson> page=new Page<>(1,100);
        return lessonMapper.selectLesson(page,lessonname);

    }

    public IPage<Lesson> selectLessonById(QueryDTO queryDTO) {
        Page<Lesson> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return lessonMapper.selectLessonById(page,queryDTO.getKeyword());
    }
}
