package com.keyboardman.tool.zhangjinsen.controller;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import com.keyboardman.tool.zhangjinsen.service.ZjsBookCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@RestController
public class ZjsBookCarController extends CommonFather {
    private static final Logger logger = LoggerFactory.getLogger(ZjsBookCarController.class);

    @Autowired
    private ZjsBookCarService zjsBookCarService;

    /**
     * 添加订单
     * @param car
     * @return
     */
    @RequestMapping("/zjsWxAddBook")
    public Map<String, String> addBookCar(CarZJS car) throws ParseException {
        Map<String, String> map = zjsBookCarService.addCarBook(car);
        return map;
    }

    /**
     * 开始时间检查
     * @param time
     * @return
     */
    @RequestMapping("/zjsWxCheckStime")
    public Map<String, String> checkStarttime(Date time) throws ParseException {
        Map<String, String> map = zjsBookCarService.checkStarttime(time);
        return map;
    }

    /**
     * 时间段检查
     * @param starttime
     * @param endtime
     * @return
     */
    @RequestMapping("/zjsWxCheckTime")
    public Map<String, String> checktime(Date starttime, Date endtime) throws ParseException {
        Map<String, String> map = zjsBookCarService.checktime(starttime, endtime);
        return map;
    }

    /**
     * 添加评价
     * @param bookid
     * @param feedback
     * @return
     */
    @RequestMapping("/zjsWxAddFeedback")
    public Map<String, String> addBookFeedback(String bookid, String feedback){
        Map<String, String> map = zjsBookCarService.addBookFeedback(bookid, feedback);
        return map;
    }

    /**
     * 根据bookid查询
     * @param bookid
     * @return
     */
    @RequestMapping("/zjsWxSelectCarByBid")
    public CarZJS selectCarByBookid(String bookid){
        CarZJS data = zjsBookCarService.selectCarByBookid(bookid);
        return data;
    }
}
