package com.keyboardman.tool.zhangjinsen.controller;

import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import com.keyboardman.tool.zhangjinsen.service.ZjsCarCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ZjsCarCheckController {
    private static final Logger logger = LoggerFactory.getLogger(ZjsCarCheckController.class);

    @Autowired
    private ZjsCarCheckService zjsCarCheckService;


    /**
     * 查询指定日期订单
     * @param time
     * @return
     * @throws ParseException
     */
    @RequestMapping("/zjsWxCheck")
    public List<CarZJS> getCarCheckList(String username, String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (time == null){
            time = sdf.format(new Date());
        }
        Date strTime =  sdf.parse(time);
        List<CarZJS> carList = zjsCarCheckService.getCarListByTime(username, strTime);

        return carList;
    }

}
