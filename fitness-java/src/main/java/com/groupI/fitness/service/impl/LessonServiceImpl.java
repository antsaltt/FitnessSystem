package com.groupI.fitness.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.Lesson;
import com.groupI.fitness.mapper.CartMapper;
import com.groupI.fitness.mapper.LessonMapper;
import com.groupI.fitness.service.CartService;
import com.groupI.fitness.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartService cartService;

    @Override
    public Integer addLesson(Lesson lesson) {
        QueryWrapper<Lesson> wrapper = new QueryWrapper<>();
        wrapper.eq("lesson_name", lesson.getLessonName());
        Lesson lesson1=lessonMapper.selectOne(wrapper);
        if (lesson1==null){return lessonMapper.insert(lesson);}
        else{
            return -1;
        }
    }

    @Override
    public Integer updateLesson(Lesson lesson) {
        return lessonMapper.updateById(lesson);
    }


    @Override
    public Integer deleteLesson(Integer id) {

        QueryDTO queryLessonDTO = new QueryDTO();
        queryLessonDTO.setPageNo(1);
        queryLessonDTO.setPageSize(9999);
        String lessonId = String.valueOf(id);
        queryLessonDTO.setKeyword(lessonId);

//        IPage<Cart> cartWithSameLessonId = cartService.selectCartWithSameLessonId(queryLessonDTO);
//        List<Cart> cartSelect = cartWithSameLessonId.getRecords();

//        for (Cart cart : cartSelect) {
//            cartService.deleteCart(cart.getCartId());
//        }

        return lessonMapper.deleteById(id);
    }

    @Override
    public void batchDelete(List<Integer> ids) {

        for (Integer id : ids){
            QueryDTO queryLessonDTO = new QueryDTO();
            queryLessonDTO.setPageNo(1);
            queryLessonDTO.setPageSize(9999);
            String lessonId = String.valueOf(id);
            queryLessonDTO.setKeyword(lessonId);

//            IPage<Cart> cartWithSameLessonId = cartService.selectCartWithSameLessonId(queryLessonDTO);
//            List<Cart> cartSelect = cartWithSameLessonId.getRecords();
//
//            for (Cart cart : cartSelect) {
//                cartService.deleteCart(cart.getCartId());
//            }
        }


        lessonMapper.deleteBatchIds(ids);

    }

}
