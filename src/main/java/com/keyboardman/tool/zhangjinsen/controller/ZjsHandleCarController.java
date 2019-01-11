package com.keyboardman.tool.zhangjinsen.controller;

import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import com.keyboardman.tool.zhangjinsen.service.ZjsHandleCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ZjsHandleCarController {
    private static final Logger logger = LoggerFactory.getLogger(ZjsHandleCarController.class);

    @Autowired
    private ZjsHandleCarService zjsHandleCarService;

    /**
     * 获取待审批数据
     * @return
     */
    @RequestMapping("/zjsWxHandlePage")
    public List<CarZJS> getHandleList(){
        Date time = new Date();
        List<CarZJS> data = zjsHandleCarService.getHandleList(time);
        return data;
    }

    /**
     * 成功
     * @return
     */
    @RequestMapping("/zjsWxSuccessPage")
    public List<CarZJS> getSuccessList(){
        List<CarZJS> data = zjsHandleCarService.getSuccessList();
        return data;
    }

    /**
     * 个人中心
     * @param username
     * @return
     */
    @RequestMapping("/zjsWxPersonalPage")
    public List<CarZJS> getPersonalList(String username){
        List<CarZJS> data = zjsHandleCarService.getPersonalList(username);
        return data;
    }

    /**
     * 根据id获取详细信息
     * @param id
     * @return
     */
    @RequestMapping("/zjsWxDetailPage")
    public CarZJS getCarById(int id){
        CarZJS data = zjsHandleCarService.getCarById(id);
        return data;
    }

    /**
     * 审批
     * @param id
     * @param status
     * @param spyj
     * @return
     */
    @RequestMapping("/zjsWxHandle")
    public Map<String, String> handleCarById(int id, int status, String spyj){
        Map<String, String> map = zjsHandleCarService.handleCarById(id, status, spyj);
        return map;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/zjsWxDeleteCar")
    public Map<String, String> deleteCarById(int id){
        Map<String, String> map = zjsHandleCarService.deleteCarById(id);
        return map;
    }

}
