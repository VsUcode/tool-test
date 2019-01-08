package com.keyboardman.tool.zhaofeng.configuration;

import com.keyboardman.tool.zhaofeng.interceptor.LoginRequiredInterceptorZf;
import com.keyboardman.tool.zhaofeng.interceptor.PassportInterceptorZf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ConfigurationZF implements WebMvcConfigurer {

    @Autowired
    private PassportInterceptorZf passportInterceptorZf;

    @Autowired
    LoginRequiredInterceptorZf loginRequiredInterceptorZf;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){

        interceptorRegistry.addInterceptor(passportInterceptorZf);
        InterceptorRegistration loginRegistry =interceptorRegistry.addInterceptor(loginRequiredInterceptorZf);

        loginRegistry.addPathPatterns("/healthPage");
        // 排除路径
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/loginPage");
        loginRegistry.excludePathPatterns("/loginout");
        loginRegistry.excludePathPatterns("/reg");


    }

}
