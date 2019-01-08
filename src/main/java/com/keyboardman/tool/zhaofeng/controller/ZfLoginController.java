package com.keyboardman.tool.zhaofeng.controller;

import com.keyboardman.tool.zhaofeng.model.UserZF;
import com.keyboardman.tool.zhaofeng.service.ZfLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class ZfLoginController {
    private static final Logger logger = LoggerFactory.getLogger(ZfLoginController.class);

    @Autowired
    private ZfLoginService zfLoginService;

    /**
     * 普通用户注册
     * @param user
     * @return
     */
    @RequestMapping("/ZfWxReg")
    public Map<String,String> WxRegister(UserZF user) throws UnsupportedEncodingException {

        Map<String,String> data = zfLoginService.register(user);
        return data;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/ZfWxLogin")
    public UserZF WxLogin(UserZF user) throws UnsupportedEncodingException {

        Map<String,String> data = zfLoginService.login(user);
        UserZF userInfo = null;
        if (data.get("msg").equals("success")){
            userInfo = zfLoginService.getUser(user.getUsername());
        }
        return userInfo;
    }


    /**
     * 普通用户注册
     * @param user
     * @return
     */
    @RequestMapping("/ZfReg")
    public Map<String,String> register(UserZF user, HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String,String> data = zfLoginService.register(user);
        if (data.get("msg").equals("success")){
            UserZF userInfo = zfLoginService.getUser(user.getUsername());
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
    @RequestMapping("/ZfLogin")
    public Map<String,String> login(UserZF user, HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String,String> data = zfLoginService.login(user);
        if (data.get("msg").equals("success")){
            UserZF userInfo = zfLoginService.getUser(user.getUsername());
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
    @RequestMapping("/ZfLoginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}
