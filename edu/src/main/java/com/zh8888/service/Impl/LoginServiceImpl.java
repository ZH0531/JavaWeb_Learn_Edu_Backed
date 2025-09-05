package com.zh8888.service.Impl;

import com.zh8888.mapper.EmpMapper;
import com.zh8888.pojo.dto.LoginResult;
import com.zh8888.pojo.dto.Result;
import com.zh8888.pojo.entity.Emp;
import com.zh8888.service.LoginService;
import com.zh8888.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public Result login(Emp emp) {
        Emp loginEmp = empMapper.getEmpByUsernameAndPassword(emp.getUsername(), emp.getPassword());
        LoginResult loginResult = new LoginResult();

        if (loginEmp != null) {
            // 登录成功
            loginResult.setId(loginEmp.getId());
            loginResult.setUsername(loginEmp.getUsername());
            loginResult.setName(loginEmp.getName());
            // 生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            claims.put("username", loginEmp.getUsername());

            String token = jwtUtil.generateToken(claims);
            loginResult.setToken(token);

            return Result.success(loginResult);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
