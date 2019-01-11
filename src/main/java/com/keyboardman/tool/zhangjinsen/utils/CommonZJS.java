package com.keyboardman.tool.zhangjinsen.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonZJS {

    public static String MD5_SALT = "kingsen";

    public static Date addOneDay(Date time){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(time);
        rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加10天
        Date endtime = rightNow.getTime();

        return endtime;
    }


//    public static String getValue(int key){
//        Map<Integer, String> map = new HashMap<>();
//        map.put(0, "成功");
//        map.put(1, "审批中");
//        map.put(2, "已取消");
//        map.put(3, "已删除");
//
//        return map.get(key);
//    }
//
//    public static int getKey(String value){
//        Map<String, Integer> map = new HashMap<>();
//        map.put("成功", 0);
//        map.put("审批中", 1);
//        map.put("已取消", 2);
//        map.put("已删除", 3);
//
//        return map.get(value);
//    }
}
