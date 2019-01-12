package com.keyboardman.tool.zhangjinsen.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.zhangjinsen.dao.ZjsCarDao;
import com.keyboardman.tool.zhangjinsen.model.CarZJS;
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


    /**
     * 指定时间范围内订单
     * @param time
     * @return
     */
    public List<CarZJS> getCarListByTime(Date time) {
        List<CarZJS> data = new ArrayList<>();

        //日期加一天
        Date starttime = time;
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(time);
//        rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加10天
//        Date endtime = rightNow.getTime();
        Date endtime = CommonZJS.addOneDay(time);
        List<CarZJS> list = zjsCarDao.selectCheckList(starttime, endtime);
        for (CarZJS car : list){

            CarZJS c = new CarZJS();
            c.setStatus(car.getStatus());
            c.setBooker(car.getBooker());
            c.setBookerPhone(car.getBookerPhone());

            Calendar calendar = Calendar.getInstance();//日历对象
            calendar.setTime(car.getStarttime());//设置当前日期
            c.setCarUser(String.valueOf(calendar.get(Calendar.YEAR)) +"-"+ String.valueOf(calendar.get(Calendar.MONTH) + 1) +"-"+ calendar.get(Calendar.DATE));
            c.setStartSite(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +":"+ calendar.get(Calendar.MINUTE));

            calendar.setTime(car.getEndtime());//设置当前日期
            c.setEndSite(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +":"+ calendar.get(Calendar.MINUTE));
            data.add(c);
        }
        return data;
    }
}
