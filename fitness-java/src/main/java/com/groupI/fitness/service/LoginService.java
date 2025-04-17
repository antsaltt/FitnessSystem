package com.groupI.fitness.service;

import com.groupI.fitness.dto.LoginDTO;
import com.groupI.fitness.result.Result;

public interface LoginService {
    public Result loginUser(LoginDTO loginDTO);
    public Result loginStaff(LoginDTO loginDTO);
}
