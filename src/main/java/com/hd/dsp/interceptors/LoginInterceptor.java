package com.hd.dsp.interceptors;

import com.hd.dsp.utils.JwtUtil;
import com.hd.dsp.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

//拦截器，在进入网页前读取token中的jwt来获取用户信息
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            UserContext.setUserId((Integer) claims.get("id"));
            UserContext.setUserType((Integer) claims.get("type"));
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }

    }
}
