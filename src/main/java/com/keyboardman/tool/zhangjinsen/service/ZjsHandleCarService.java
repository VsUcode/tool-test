package com.keyboardman.tool.zhangjinsen.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.zhangjinsen.dao.ZjsCarDao;
import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import com.keyboardman.tool.zhangjinsen.model.UserZJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ZjsHandleCarService extends CommonFather {
    private Lock lock = new ReentrantLock();
    @Autowired
    private ZjsCarDao zjsCarDao;

    @Autowired
    private ZjsLoginService zjsLoginService;

    /**
     * 待审批
     * @return
     * @param time
     */
    public List<CarZJS> getHandleList(String username, Date time) {

        UserZJS user = zjsLoginService.getUser(username);
        if (user == null){
            throw new RuntimeException("用户不存在");
        }

        List<CarZJS> list = zjsCarDao.selectHandleList(time, RootConstant.STATUS_1);
        List<CarZJS> data = getCarList(list);
        return data;
    }

    /**
     * 成功
     * @return
     */
    public List<CarZJS> getSuccessList(String username, Date time) {
        UserZJS user = zjsLoginService.getUser(username);
        if (user == null){
            throw new RuntimeException("用户不存在");
        }

        List<CarZJS> list = zjsCarDao.selectSuccessList(time, RootConstant.STATUS_0);

        List<CarZJS> data = getCarList(list);
        return data;
    }

    /**
     * 个人中心
     * @param username
     * @return
     */
    public List<CarZJS> getPersonalList(String username) {
        super.validateEmpty("username", username);
        Date time = new Date();
        List<CarZJS> list = zjsCarDao.selectPersonalList(username, time);

        List<CarZJS> data = getCarList(list);
        return data;
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
        Map<String, String> map = new HashMap<>();

       lock.lock();
        try{
            CarZJS carZJS = zjsCarDao.selectCarById(id);
            if (carZJS.getStatus() != 1){
                map.put("msg", "审批失败，已被他人审批");
                return map;
            }
            zjsCarDao.handleCar(id, status, spyj);
            map.put("msg", "success");
        }catch (Exception e){
            map.put("msg", "数据库错误");
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

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
        if (carZJS.getStatus() != 1  && carZJS.getStatus() != 0){
            map.put("msg", "删除失败，已被他人删除");
            return map;
        }

        try{
            zjsCarDao.deleteCar(id, RootConstant.STATUS_3);
            map.put("msg", "success");
        }catch (Exception e){
            map.put("msg", "数据库错误");
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 简易封装
     * @return
     */
    private List<CarZJS> getCarList(List<CarZJS> list){
        List<CarZJS> data = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();//日历对象
        Calendar calendarE = Calendar.getInstance();//日历对象
        for (CarZJS car : list){

            CarZJS c = new CarZJS();
            c.setStatus(car.getStatus());
            c.setId(car.getId());
            c.setCarUser(car.getCarUser());
            c.setUserPhone(car.getUserPhone());

            calendarE.setTime(car.getEndtime());//设置当前日期
            calendar.setTime(car.getStarttime());//设置当前日期

            c.setBooker(String.valueOf(calendar.get(Calendar.YEAR)) +"/"+ String.valueOf(calendar.get(Calendar.MONTH) + 1) +"/"+ calendar.get(Calendar.DATE)+
                    "--"+String.valueOf(calendarE.get(Calendar.YEAR)) +"/"+ String.valueOf(calendarE.get(Calendar.MONTH) + 1) +"/"+ calendarE.get(Calendar.DATE));
            c.setStartSite(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +":"+ calendar.get(Calendar.MINUTE));

            c.setEndSite(String.valueOf(calendarE.get(Calendar.HOUR_OF_DAY)) +":"+ calendarE.get(Calendar.MINUTE));
            data.add(c);
        }

        return  data;
    }
}
