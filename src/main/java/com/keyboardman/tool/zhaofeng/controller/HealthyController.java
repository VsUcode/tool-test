package com.keyboardman.tool.zhaofeng.controller;

import com.keyboardman.tool.zhaofeng.model.HealthZF;
import com.keyboardman.tool.zhaofeng.model.HostHolderZF;
import com.keyboardman.tool.zhaofeng.service.HealthyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthyController {
    private static final Logger logger = LoggerFactory.getLogger(HealthyController.class);


    @Autowired
    private HostHolderZF hostHolderZF;

    @Autowired
    private HealthyService healthyService;

    /**
     * 查看已有的
     * @return
     */
    @RequestMapping("/WxSelectHealthy")
    public HealthZF selectHealthy(String username){
        HealthZF entity = healthyService.selectHealthy(username);
        return entity;
    }

    /**
     * 查看已有的 通过id
     * @return
     */
    @RequestMapping("/selectHealthyById")
    public HealthZF selectHealthyById(String id){
        Map<String, String> map = new HashMap<>();

        HealthZF entity = healthyService.selectHealthyById(id);

        return entity;
    }

    /**
     * 添加病史
     * @param entity
     * @return
     */
    @RequestMapping("/WxAddHealthy")
    public Map<String, String> addHealthy(HealthZF entity){
        Map<String, String> map = new HashMap<>();
        map = healthyService.addHealthy(entity);

        return map;
    }


    /**
     * 更新
     * @param entity
     * @return
     */
    @RequestMapping("/WxUpdateHealthy")
    public Map<String, String> updateHealthy(HealthZF entity){
        Map<String, String> map = new HashMap<>();
        map = healthyService.updateHealthy(entity);

        return map;
    }


    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping("/WxDeleteHealthy")
    public Map<String, String> deleteHealthy(String username){
        Map<String, String> map = new HashMap<>();
        map = healthyService.deleteHealthy(username);

        return map;
    }




}
