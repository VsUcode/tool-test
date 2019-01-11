package com.keyboardman.tool.zhangjinsen.controller;

import com.keyboardman.tool.zhangjinsen.model.UserZJS;
import com.keyboardman.tool.zhangjinsen.service.ZjsLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class ZjsLoginController {
    private static final Logger logger = LoggerFactory.getLogger(ZjsLoginController.class);

    @Autowired
    private ZjsLoginService zjsLoginService;

    /**
     * 普通用户注册
     * @param user
     * @return
     */
    @RequestMapping("/ZjsWxReg")
    public Map<String,String> WxRegister(UserZJS user) throws UnsupportedEncodingException {

        Map<String,String> data = zjsLoginService.register(user);
        return data;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/ZjsWxLogin")
    public UserZJS WxLogin(UserZJS user) throws UnsupportedEncodingException {

        Map<String,String> data = zjsLoginService.login(user);
        UserZJS userInfo = null;
        if (data.get("msg").equals("success")){
            userInfo = zjsLoginService.getUser(user.getUsername());
        }
        return userInfo;
    }


//    /**
//     * 普通用户注册
//     * @param user
//     * @return
//     */
//    @RequestMapping("/ZfReg")
//    public Map<String,String> register(UserZF user, HttpServletRequest request) throws UnsupportedEncodingException {
//
//        Map<String,String> data = zfLoginService.register(user);
//        if (data.get("msg").equals("success")){
//            UserZF userInfo = zfLoginService.getUser(user.getUsername());
//            userInfo.setPassword("");
//            request.getSession().setAttribute("userinfo", userInfo);
//        }
//
//        return data;
//    }
//
//    /**
//     * 登录
//     * @param user
//     * @return
//     */
//    @RequestMapping("/ZfLogin")
//    public Map<String,String> login(UserZF user, HttpServletRequest request) throws UnsupportedEncodingException {
//
//        Map<String,String> data = zfLoginService.login(user);
//        if (data.get("msg").equals("success")){
//            UserZF userInfo = zfLoginService.getUser(user.getUsername());
//            userInfo.setPassword("");
//            request.getSession().setAttribute("userinfo", userInfo);
//        }
//        return data;
//    }



//    /**
//     * 登出
//     * @param request
//     * @return
//     */
//    @RequestMapping("/ZfLoginout")
//    public String loginOut(HttpServletRequest request) {
//        request.getSession().invalidate();
//        return "redirect:/";
//    }

}
