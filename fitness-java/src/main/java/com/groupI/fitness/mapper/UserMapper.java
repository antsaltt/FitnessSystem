package com.groupI.fitness.mapper;

import com.groupI.fitness.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectUserPage(Page<User> page,String keyword);
    IPage<User> selectUser(Page<User> page,String keyword);
}
