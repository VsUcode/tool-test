package com.keyboardman.tool.zhaofeng.configuration;

import com.keyboardman.tool.zhaofeng.interceptor.LoginRequiredInterceptor;
import com.keyboardman.tool.zhaofeng.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class Configuration implements WebMvcConfigurer {

    @Autowired
    private PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){

        interceptorRegistry.addInterceptor(passportInterceptor);
        InterceptorRegistration loginRegistry =interceptorRegistry.addInterceptor(loginRequiredInterceptor);

        loginRegistry.addPathPatterns("/healthPage");
        // 排除路径
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/loginPage");
        loginRegistry.excludePathPatterns("/loginout");
        loginRegistry.excludePathPatterns("/reg");


    }

}
