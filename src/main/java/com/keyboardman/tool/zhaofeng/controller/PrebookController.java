package com.keyboardman.tool.zhaofeng.controller;

import com.keyboardman.tool.zhaofeng.model.HostHolderZF;
import com.keyboardman.tool.zhaofeng.model.PrebookZF;
import com.keyboardman.tool.zhaofeng.service.PrebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PrebookController {
    private static final Logger logger = LoggerFactory.getLogger(PrebookController.class);


    @Autowired
    private HostHolderZF hostHolderZF;

    @Autowired
    private PrebookService prebookService;

    /**
     * 查看自己的预约
     * @return
     */
    @RequestMapping("/WxSelectBook")
    public PrebookZF selectHealthy(){
        Map<String, String> map = new HashMap<>();

        String username = hostHolderZF.getUser().getUsername();
        PrebookZF entity = prebookService.selectBook(username);

        return entity;
    }

    /**
     * 添加病史
     * @param entity
     * @return
     */
    @RequestMapping("/WxAddBook")
    public Map<String, String> addBook(PrebookZF entity){
        Map<String, String> map = new HashMap<>();

        String username = hostHolderZF.getUser().getUsername();
        entity.setUsername(username);

        map = prebookService.addBook(entity);

        return map;
    }

    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping("/WxDeleteBook")
    public Map<String, String> deleteBook(){
        Map<String, String> map = new HashMap<>();

        String username = hostHolderZF.getUser().getUsername();
        map = prebookService.deleteBook(username);

        return map;
    }

    /**
     * 管理员根据type查看预约列表
     * @return
     */
    @RequestMapping("/bookList")
    public List<PrebookZF> bookList(String type){
        List<PrebookZF> list = new ArrayList<>();

        list = prebookService.selectBookList(type);

        return list;
    }


}
