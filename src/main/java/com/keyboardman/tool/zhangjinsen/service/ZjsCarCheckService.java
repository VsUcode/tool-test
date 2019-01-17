package com.keyboardman.tool.zhangjinsen.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.zhangjinsen.dao.ZjsCarDao;
import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import com.keyboardman.tool.zhangjinsen.model.UserZJS;
import com.keyboardman.tool.zhangjinsen.utils.CommonZJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ZjsCarCheckService extends CommonFather {

    @Autowired
    private ZjsCarDao zjsCarDao;

    @Autowired
    private ZjsLoginService zjsLoginService;


    /**
     * 指定时间范围内订单
     * @param time
     * @return
     */
    public List<CarZJS> getCarListByTime(String username, Date time) {
        List<CarZJS> data = new ArrayList<>();
        UserZJS user = zjsLoginService.getUser(username);
        if (user == null){
            throw new RuntimeException("用户不存在");
        }
        //日期加一天
        Date starttime = time;

        Date endtime = CommonZJS.addOneDay(time);

        List<CarZJS> list = zjsCarDao.selectCheckList(starttime, endtime);
        // 跨天的
        List<CarZJS> list1 = zjsCarDao.selectCheckList1(time);
        list.addAll(list1);
        Calendar calendar = Calendar.getInstance();//日历对象
        Calendar calendarE = Calendar.getInstance();//日历对象
        for (CarZJS car : list){

            CarZJS c = new CarZJS();
            c.setStatus(car.getStatus());
            c.setBooker(car.getBooker());
            c.setBookerPhone(car.getBookerPhone());

            calendarE.setTime(car.getEndtime());//设置当前日期
            calendar.setTime(car.getStarttime());//设置当前日期
            c.setCarUser(String.valueOf(calendar.get(Calendar.YEAR)) +"/"+ String.valueOf(calendar.get(Calendar.MONTH) + 1) +"/"+ calendar.get(Calendar.DATE)+
                    "--"+String.valueOf(calendarE.get(Calendar.YEAR)) +"/"+ String.valueOf(calendarE.get(Calendar.MONTH) + 1) +"/"+ calendarE.get(Calendar.DATE));
            c.setStartSite(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +":"+ calendar.get(Calendar.MINUTE));
            c.setEndSite(String.valueOf(calendarE.get(Calendar.HOUR_OF_DAY)) +":"+ calendarE.get(Calendar.MINUTE));
            data.add(c);
        }
        return data;
    }
}
