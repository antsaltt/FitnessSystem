package com.groupI.fitness.service.impl;

import com.groupI.fitness.dto.LoginDTO;
import com.groupI.fitness.entity.Staff;
import com.groupI.fitness.entity.User;
import com.groupI.fitness.mapper.StaffMapper;
import com.groupI.fitness.mapper.UserMapper;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.LoginService;
import com.groupI.fitness.vo.LoginStaffVO;
import com.groupI.fitness.vo.LoginVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result loginUser(LoginDTO loginDTO) {
        if (StringUtils.isEmpty(loginDTO.getLoginName())){
            System.out.println("Login name cannot be empty!");
            return new Result(400,"Login name cannot be empty!","");
        }
        if (StringUtils.isEmpty(loginDTO.getPassword())){
            System.out.println("Password cannot be empty!");
            return new Result(400,"Password cannot be empty!","");
        }
        // Query user by login name
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginDTO.getLoginName());
        User uer=userMapper.selectOne(wrapper);
        // Compare password
        System.out.println(uer == null);
        if (uer!=null&&uer.getPassword().equals(loginDTO.getPassword())){
            LoginVO loginVO=new LoginVO();
            loginVO.setId(uer.getId());
            // Generate a uuid for token
            loginVO.setToken(UUID.randomUUID().toString());
            loginVO.setUser(uer);
            return new Result(200,"",loginVO);
        }
        return new Result(401,"Login failed!","");
    }


    @Autowired
    private StaffMapper staffMapper;
    @Override
    public Result loginStaff(LoginDTO loginDTO) {
        if (StringUtils.isEmpty(loginDTO.getLoginName())){
            System.out.println("Login name cannot be empty!");
            return new Result(400,"Login name cannot be empty!","");
        }
        if (StringUtils.isEmpty(loginDTO.getPassword())){
            System.out.println("Password cannot be empty!");
            return new Result(400,"Password cannot be empty!","");
        }
        // Query user by login name
        QueryWrapper<Staff> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("login_name", loginDTO.getLoginName());
        Staff staff=staffMapper.selectOne(wrapper1);
        // Compare password
        System.out.println(staff == null);
        if (staff!=null&&staff.getPassword().equals(loginDTO.getPassword())){
            LoginStaffVO loginStaffVO=new LoginStaffVO();
            loginStaffVO.setId(staff.getId());
            // Generate a uuid for token
            loginStaffVO.setToken(UUID.randomUUID().toString());
            loginStaffVO.setStaff(staff);
            return new Result(200,"",loginStaffVO);
        }
        return new Result(401,"Login failed!","");
    }
}
