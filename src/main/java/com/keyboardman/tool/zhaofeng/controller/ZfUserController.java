package com.keyboardman.tool.zhaofeng.controller;

import com.keyboardman.tool.zhaofeng.model.HostHolderZF;
import com.keyboardman.tool.zhaofeng.service.ZfUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ZfUserController {
    private static final Logger logger = LoggerFactory.getLogger(ZfUserController.class);


    @Autowired
    private HostHolderZF hostHolderZF;

    @Autowired
    private ZfUserService zfUserService;

    /**
     * 注销用户
     * @param password
     * @return
     */
    @RequestMapping("/ZfWxDeleteUser")
    public Map<String, String> deleteUser(String password) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        String username = hostHolderZF.getUser().getUsername();
        map = zfUserService.deleteUser(username, password);

        return map;
    }

    @RequestMapping("/ZfWxChangePwd")
    public Map<String, String> changePwd(String username, String password, String newPassword) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

//        String username = hostHolderZF.getUser().getUsername();
        map = zfUserService.changePwd(username, password, newPassword);

        return map;
    }

}
