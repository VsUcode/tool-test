package com.keyboardman.tool.zhaofeng.interceptor;

import com.keyboardman.tool.zhaofeng.model.HostHolderZF;
import com.keyboardman.tool.zhaofeng.model.UserZF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PassportInterceptorZf implements HandlerInterceptor {


    @Autowired
    private HostHolderZF hostHolderZF;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserZF userZF = (UserZF) request.getSession().getAttribute("userinfo");
        if (userZF != null){
            hostHolderZF.setUsers(userZF);
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView != null && hostHolderZF.getUser() != null){
            modelAndView.addObject("user", hostHolderZF.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        hostHolderZF.clear();
    }
}
