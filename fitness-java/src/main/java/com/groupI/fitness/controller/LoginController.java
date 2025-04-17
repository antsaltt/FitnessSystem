package com.groupI.fitness.controller;

import com.groupI.fitness.dto.LoginDTO;
import com.groupI.fitness.result.Result;
import com.groupI.fitness.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;


    @PostMapping(value = "/api/loginUser")
    @CrossOrigin
    public Result loginUser(@RequestBody LoginDTO loginDTO){
        return loginService.loginUser(loginDTO);
    }
    @PostMapping(value = "/api/loginStaff")

    public Result loginStaff(@RequestBody LoginDTO loginDTO){
        return loginService.loginStaff(loginDTO);
    }
}