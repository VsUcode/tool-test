package com.keyboardman.tool.zhangjinsen.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.zhangjinsen.dao.ZjsCarDao;
import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZjsHandleCarService extends CommonFather {

    @Autowired
    private ZjsCarDao zjsCarDao;

    /**
     * 待审批
     * @return
     * @param time
     */
    public List<CarZJS> getHandleList(Date time) {
        List<CarZJS> list = zjsCarDao.selectHandleList(time, RootConstant.STATUS_1);
        return list;
    }

    /**
     * 成功
     * @return
     */
    public List<CarZJS> getSuccessList() {
        List<CarZJS> list = zjsCarDao.selectSuccessList(RootConstant.STATUS_0);
        return list;
    }

    /**
     * 个人中心
     * @param username
     * @return
     */
    public List<CarZJS> getPersonalList(String username) {
        super.validateEmpty("username", username);
        List<CarZJS> list = zjsCarDao.selectPersonalList(username);
        return list;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public CarZJS getCarById(int id) {
        super.validateEmpty("id", id);
        CarZJS data = zjsCarDao.selectCarById(id);
        return data;
    }

    /**
     * 审批
     * @param id
     * @param status
     * @param spyj
     * @return
     */
    public Map<String, String> handleCarById(int id, int status, String spyj) {
        super.validateEmpty("id", id);
        super.validateEmpty("status", status);
        super.validateEmpty("spyj", spyj);
        Map<String, String> map = new HashMap<>();

        CarZJS carZJS = zjsCarDao.selectCarById(id);
        if (carZJS.getStatus() != 1){
            map.put("msg", "审批失败，已被他人审批");
            return map;
        }
        int result = 0;
        result = zjsCarDao.handleCar(id, status, spyj);

        if (result == 1){
            map.put("msg", "success");
            return map;
        }

        map.put("msg", "审批失败");
        return map;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Map<String, String> deleteCarById(int id) {
        super.validateEmpty("id", id);
        Map<String, String> map = new HashMap<>();

        CarZJS carZJS = zjsCarDao.selectCarById(id);
        if (carZJS.getStatus() != 1){
            map.put("msg", "删除失败，已被他人删除");
            return map;
        }
        int result = 0;
        result = zjsCarDao.deleteCar(id, RootConstant.STATUS_3);

        if (result == 1){
            map.put("msg", "success");
            return map;
        }

        map.put("msg", "删除失败");
        return map;
    }
}
