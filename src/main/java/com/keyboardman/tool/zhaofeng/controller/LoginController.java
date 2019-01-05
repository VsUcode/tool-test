package com.keyboardman.tool.zhaofeng.controller;

import com.keyboardman.tool.zhaofeng.model.UserZF;
import com.keyboardman.tool.zhaofeng.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * 普通用户注册
     * @param user
     * @return
     */
    @RequestMapping("/reg")
    public Map<String,String> register(UserZF user, HttpServletRequest request){

        Map<String,String> data = loginService.register(user);
        if (data.get("msg").equals("success")){
            UserZF userInfo = loginService.getUser(user.getUsername());
            userInfo.setPassword("");
            request.getSession().setAttribute("userinfo", userInfo);
        }

        return data;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Map<String,String> login(UserZF user, HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String,String> data = loginService.login(user);

        if (data.get("msg").equals("success")){
            UserZF userInfo = loginService.getUser(user.getUsername());
            userInfo.setPassword("");
            request.getSession().setAttribute("userinfo", userInfo);
        }
        return data;
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}
