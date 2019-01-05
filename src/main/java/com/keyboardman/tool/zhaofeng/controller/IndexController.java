package com.keyboardman.tool.zhaofeng.controller;

import com.keyboardman.tool.zhaofeng.model.UserZF;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String homePage(){
        return "zf/index";
    }

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "zf/login";
    }

    @RequestMapping("/healthPage")
    public String healthPage(HttpServletRequest request){
        UserZF userZF = (UserZF) request.getSession().getAttribute("userinfo");

        // todo 根据权限加载相应页面
        return "zf/health";
    }

    @RequestMapping("/onlinePage")
    public String onlinePage(){
        return "zf/online";
    }

    @RequestMapping("/bookPage")
    public String bookPage(){
        return "zf/book";
    }

    @RequestMapping("/commonPage")
    public String commonPage(){
        return "zf/common";
    }

    @RequestMapping("/shopPage")
    public String shopPage(){
        return "zf/shop";
    }

    @RequestMapping("/commentPage")
    public String commentPage(){
        return "zf/comment";
    }
}
