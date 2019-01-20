package com.keyboardman.tool.root.controller;

import com.keyboardman.tool.root.model.HostHolder;
import com.keyboardman.tool.root.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    /**
     * 注销用户
     * @param password
     * @return
     */
    @RequestMapping("/deleteUser")
    public Map<String, String> deleteUser(String password) throws UnsupportedEncodingException {
        String username = hostHolder.getUser().getUsername();
        Map<String, String> map = userService.deleteUser(username, password);

        return map;
    }

    /**
     * 更改密码
     * @param password
     * @param newPassword
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/changePwd")
    public Map<String, String> changePwd(String password, String newPassword) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        String username = hostHolder.getUser().getUsername();
        map = userService.changePwd(username, password, newPassword);

        return map;
    }

}
