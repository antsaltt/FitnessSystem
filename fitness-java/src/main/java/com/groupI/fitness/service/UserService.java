package com.groupI.fitness.service;

import com.groupI.fitness.dto.QueryDTO;
import com.groupI.fitness.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface UserService {

    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
    IPage<User> selectUserPage(QueryDTO queryDTO);

    Integer addUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(Integer id);

    void batchDelete(List<Integer> ids);
    IPage<User> selectUser(QueryDTO queryDTO);
}
