package com.groupI.fitness.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.groupI.fitness.entity.Staff;


public interface StaffMapper extends BaseMapper<Staff> {
    IPage<Staff> selectUserPage(Page<Staff> page, String keyword);
}
