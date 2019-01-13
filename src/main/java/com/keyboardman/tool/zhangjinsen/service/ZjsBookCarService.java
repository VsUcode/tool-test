package com.keyboardman.tool.zhangjinsen.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.zhangjinsen.dao.ZjsCarDao;
import com.keyboardman.tool.zhangjinsen.dao.ZjsLoginDao;
import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import com.keyboardman.tool.zhangjinsen.model.UserZJS;
import com.keyboardman.tool.zhangjinsen.utils.CommonZJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZjsBookCarService extends CommonFather {

    @Autowired
    private ZjsCarDao zjsCarDao;

    @Autowired
    private ZjsCarCheckService zjsCarCheckService;

    @Autowired
    private ZjsLoginDao zjsLoginDao;

    /**
     * 添加
     * @param car
     * @return
     */
    public Map<String, String> addCarBook(CarZJS car) throws ParseException {
        super.validateEmpty("booker", car.getBooker());
        super.validateEmpty("bookerPhone", car.getBookerPhone());
        super.validateEmpty("carUser", car.getCarUser());
        super.validateEmpty("userPhone", car.getUserPhone());
        super.validateEmpty("starttime", car.getStarttime());
        super.validateEmpty("endtime", car.getEndtime());
        super.validateEmpty("startSite", car.getStartSite());
        super.validateEmpty("endSite", car.getEndSite());

        Map<String, String> map = checktime(car.getStarttime(), car.getEndtime());
        if (!map.get("msg").equals("success")){
            return map;
        }
        UserZJS userZJS = zjsLoginDao.selectByName(car.getBooker(), RootConstant.STATUS_0);
        if (userZJS!=null && userZJS.getPower()==0){
            car.setStatus(RootConstant.STATUS_0);
        }else if (userZJS!=null && userZJS.getPower()==1){
            car.setStatus(RootConstant.STATUS_1);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDhhmmss");
        car.setBookid(sdf.format(car.getStarttime()));
        int result = 0;
        result = zjsCarDao.addBook(car);
        if (result == 1){
            map.put("msg", "success");
            return map;
        }

        map.put("msg", "当前时间段被使用");
        return map;
    }

    /**
     * 添加评价
     * @param bookid
     * @param feedback
     * @return
     */
    public Map<String, String> addBookFeedback(String bookid, String feedback) {
        super.validateEmpty("bookid", bookid);
        super.validateEmpty("feedback", feedback);

        Map<String, String> map = new HashMap<>();
        int result = 0;
        CarZJS carZJS = selectCarByBookid(bookid);
        if (carZJS != null){
            result = zjsCarDao.addBookFeedback(bookid, feedback);
        }else{
            map.put("msg", "订单不存在");
            return map;
        }
        if (result == 1){
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "未知原因失败");
        return map;
    }

    /**
     * 根据bookid查询相关信息
     * @param bookid
     * @return
     */
    public CarZJS selectCarByBookid(String bookid) {
        super.validateEmpty("bookid", bookid);
        CarZJS car = zjsCarDao.selectCarByBookid(bookid);

        CarZJS carZJS = new CarZJS();
        carZJS.setStatus(car.getStatus());
        carZJS.setId(car.getId());
        carZJS.setCarUser(car.getCarUser());
        carZJS.setUserPhone(car.getUserPhone());

        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(car.getStarttime());//设置当前日期
        carZJS.setBooker(String.valueOf(calendar.get(Calendar.YEAR)) +"-"+ String.valueOf(calendar.get(Calendar.MONTH) + 1) +"-"+ calendar.get(Calendar.DATE));
        carZJS.setStartSite(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +":"+ calendar.get(Calendar.MINUTE));

        calendar.setTime(car.getEndtime());//设置当前日期
        carZJS.setEndSite(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) +":"+ calendar.get(Calendar.MINUTE));

        return carZJS;
    }

    /**
     * 开始时间检查
     * @param time
     * @return
     */
    public Map<String, String> checkStarttime(Date time) throws ParseException {
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newtime = null;
        if (time == null){
            time = new Date();
        }
        String strTime = sdf.format(time);
        newtime =  sdf.parse(strTime);
        Date endtime = CommonZJS.addOneDay(time);
        List<CarZJS> carList = zjsCarDao.selectCheckList(newtime, endtime);
//        List<CarZJS> carList = zjsCarCheckService.getCarListByTime(newtime);
        for (CarZJS c : carList ){
            //当前时间大于开始时间 必然大于结束时间
            if (time.getTime() >= c.getStarttime().getTime() && time.getTime() <= c.getEndtime().getTime()){
                map.put("msg", "当前开始时间不可用");
                return map;
            }
        }
        map.put("msg", "success");
        return map;
    }

    /**
     * 整段时间检查
     * @param starttime
     * @param endtime
     * @return
     */
    public Map<String, String> checktime(Date starttime, Date endtime) throws ParseException {
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newtime = null;
        if (starttime == null || endtime ==null){
            map.put("msg", "时间不能为空");
            return map;
        }
        if (starttime.getTime() >= endtime.getTime()){
            map.put("msg", "开始时间不能大于结束时间");
            return map;
        }

        map = checkStarttime(starttime);
        if (!map.get("msg").equals("success")){
            return map;
        }

        String strTime = sdf.format(starttime);
        Date newStime =  sdf.parse(strTime);
        Date newNtime = CommonZJS.addOneDay(newStime);
        List<CarZJS> carList = zjsCarDao.selectCheckList(newStime, newNtime);
        for (CarZJS c : carList ){
            //当前开始时间小于开始时间 当前结束时间小于开始时间
            if (starttime.getTime() <= c.getStarttime().getTime() && endtime.getTime() >= c.getStarttime().getTime()){
                map.put("msg", "当前结束时间不可用");
                return map;
            }
            //当前开始时间大于开始时间 当前开始时间大于结束时间
            if (starttime.getTime() >= c.getStarttime().getTime() && starttime.getTime() <= c.getEndtime().getTime()){
                map.put("msg", "当前结束时间不可用");
                return map;
            }

        }
        map.put("msg", "success");
        return map;
    }

}
