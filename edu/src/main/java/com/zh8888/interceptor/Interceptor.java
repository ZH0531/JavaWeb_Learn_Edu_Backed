package com.zh8888.interceptor;

import com.zh8888.utils.CurrentHolder;
import com.zh8888.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 判断token是否为空
        if (token == null || token.isEmpty()) {
            response.setStatus(401); //
            return false;
        }
        // 解析token
        try {
            Map<String, Object> claims = jwtUtil.parseToken(token);
            // 将用户id保存到当前线程中
            CurrentHolder.setCurrentId((Integer) claims.get("id"));
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        // 放行
        return true;
    }
}
