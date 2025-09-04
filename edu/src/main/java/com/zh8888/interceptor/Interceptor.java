package com.zh8888.interceptor;

import com.zh8888.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
            jwtUtil.parseToken(token);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        // 放行
        return true;
    }
}
