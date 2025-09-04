package com.zh8888.controller;

import com.zh8888.pojo.dto.Result;
import com.zh8888.pojo.entity.Emp;
import com.zh8888.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class loginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录：{}", emp);
        return loginService.login(emp);
    }

}
