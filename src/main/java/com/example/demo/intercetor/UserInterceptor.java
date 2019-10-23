package com.example.demo.intercetor;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行到了preHandle方法");
//        System.out.println(handler);
//        User user = (User) request.getSession().getAttribute("session_user");
//        if (user == null) {
//            response.sendRedirect(request.getContextPath() + "/toIndex");//拦截后跳转的方法
//            System.out.println("已成功拦截并转发跳转");
//            return false;
//        }
//        System.out.println("合格不需要拦截，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行到了postHandle方法");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("执行到了afterCompletion方法");
    }
}
