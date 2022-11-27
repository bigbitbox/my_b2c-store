package com.ethercat.admin.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: b2c-store
 * @description: 登录保护拦截器
 * @author: Ethercat
 * @create: 2022-11-27 10:41
 **/

public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userInfo = request.getSession().getAttribute("userInfo");

        if (userInfo != null) {
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;

        }
        //false 拦截 | true 放行
    }
}
