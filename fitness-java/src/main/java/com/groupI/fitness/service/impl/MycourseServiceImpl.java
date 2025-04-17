package com.groupI.fitness.service.impl;

import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Mycourse;
import com.groupI.fitness.mapper.MycourseMapper;
import com.groupI.fitness.service.MycourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MycourseServiceImpl implements MycourseService {
    @Autowired
    private MycourseMapper mycourseMapper;

    @Override
    public Integer addMycourse(Mycourse mycourse) {
//        if (mycourse1==null){return mycourseMapper.insert(mycourse);}
//        QueryWrapper<Mycourse> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id", mycourse.getMycourseId());
//        Mycourse mycourse1=mycourseMapper.selectOne(wrapper);

        List<Mycourse> mycourseSelect = mycourseMapper.selectMycourse(new Page<>(1,100),mycourse.getUserId().toString()).getRecords();
        //if (Objects.equals(mycourse1.getLessonId(), mycourse.getLessonId())&&Objects.equals(mycourse1.getLessonSize(), mycourse.getLessonId()))
        for (Mycourse tmp:mycourseSelect){
            if (Objects.equals(tmp.getLessonId(),mycourse.getLessonId())){
                tmp.setLessonName(tmp.getLessonName());
                return mycourseMapper.updateById(tmp);
            }
        }
        return mycourseMapper.insert(mycourse);
        //return -1;

    }

    @Override
    public Integer updateMycourse(Mycourse mycourse) {
        return mycourseMapper.updateById(mycourse);
    }
    @Override
    public Integer deleteMycourse(Integer id) {
        return mycourseMapper.deleteById(id);
    }


    @Override
    public void batchDelete(List<Integer> ids) {
        mycourseMapper.deleteBatchIds(ids);
    }
    @Override
    public IPage<Mycourse> selectMycoursePage(QueryDTO queryDTO) {
        Page<Mycourse> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return mycourseMapper.selectMycoursePage(page,queryDTO.getKeyword());
    }
    @Override
    public IPage<Mycourse> selectMycourse(QueryDTO queryDTO) {
        Page<Mycourse> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return mycourseMapper.selectMycourse(page,queryDTO.getKeyword());
    }


//    @Override
//    public IPage<Mycourse> selectMycourseWithSameLessonId(QueryDTO queryDTO) {
//        return null;
//    }

//    @Override
//    public IPage<Mycourse> selectMycourseWithSameLessonId(QueryDTO queryDTO) {
//        return null;
//    }

    @Override
    public IPage<Mycourse> selectMycourseWithSameLessonId(QueryDTO queryDTO) {
        Page<Mycourse> page=new Page<>(queryDTO.getPageNo(),queryDTO.getPageSize());
        return mycourseMapper.selectMycourseWithSameLessonId(page,queryDTO.getKeyword());
    }
}
