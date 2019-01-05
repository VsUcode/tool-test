package com.keyboardman.tool.zhaofeng.service;

import com.keyboardman.tool.zhaofeng.dao.HealthyDao;
import com.keyboardman.tool.zhaofeng.model.HealthZF;
import com.keyboardman.tool.zhaofeng.utils.CommonZF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HealthyService {

    @Autowired
    private HealthyDao healthyDao;

    /**
     * 添加病史
     * @param entity
     * @return
     */
    public Map<String, String> addHealthy(HealthZF entity) {
        Map<String, String> map = new HashMap<>();
        // todo 非空判断

        HealthZF healthZF = healthyDao.selectByUsername(entity.getUsername(), CommonZF.STATUS_0);
        if (healthZF != null){
            map.put("msg", "已有病史模板");
            return map;
        }
        entity.setStatus(CommonZF.STATUS_0);
        int flag = healthyDao.insertHealth(entity);
        if (flag == 1){
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    public Map<String, String> updateHealthy(HealthZF entity) {
        Map<String, String> map = new HashMap<>();
        // todo 非空判断

        HealthZF healthZF = healthyDao.selectByUsername(entity.getUsername(), CommonZF.STATUS_0);
        if (healthZF != null){
            healthyDao.deleteByid(healthZF.getId(), CommonZF.STATUS_1);
        }
        entity.setStatus(CommonZF.STATUS_0);
        int flag = healthyDao.insertHealth(entity);
        if (flag == 1){
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * 删除
     * @param username
     * @return
     */
    public Map<String, String> deleteHealthy(String username) {
        Map<String, String> map = new HashMap<>();
        // todo 非空判断
        HealthZF healthZF = healthyDao.selectByUsername(username, CommonZF.STATUS_0);
        int flag = healthyDao.deleteByid(healthZF.getId(), CommonZF.STATUS_1);
        if (flag == 1){
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * 查询
     * @param username
     * @return
     */
    public HealthZF selectHealthy(String username) {
        // todo 非空判断

        HealthZF healthZF = healthyDao.selectByUsername(username, CommonZF.STATUS_0);

        return healthZF;
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    public HealthZF selectHealthyById(String id) {
        // todo 非空判断

        HealthZF healthZF = healthyDao.selectById(id);

        return healthZF;
    }
}
