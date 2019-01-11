package com.keyboardman.tool.zhangjinsen.controller;

import com.keyboardman.tool.zhangjinsen.service.ZjsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ZjsUserController {

    private static final Logger logger = LoggerFactory.getLogger(ZjsUserController.class);

    @Autowired
    private ZjsUserService zjsUserService;

    /**
     * 注销用户
     * @param password
     * @return
     */
    @RequestMapping("/zjsWxDeleteUser")
    public Map<String, String> deleteUser(String username, String password) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        map = zjsUserService.deleteUser(username, password);

        return map;
    }

    /**
     * 更改密码
     * @param username
     * @param password
     * @param newPassword
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/zjsWxChangePwd")
    public Map<String, String> changePwd(String username, String password, String newPassword) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        map = zjsUserService.changePwd(username, password, newPassword);

        return map;
    }

    /**
     * 更改电话
     * @param username
     * @param phone
     * @return
     */
    @RequestMapping("/zjsWxChangePhone")
    public Map<String, String> changePhone(String username, String phone){
        Map<String, String> map = new HashMap<>();

        map = zjsUserService.changePhone(username, phone);

        return map;
    }

}
