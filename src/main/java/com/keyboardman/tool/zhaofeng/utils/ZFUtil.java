package com.keyboardman.tool.zhaofeng.utils;

import com.keyboardman.tool.zhaofeng.model.UserZF;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ZFUtil {

    public static Map<String, String> identifyUser(UserZF user){
        Map<String, String> map = new HashMap<>();

        if (StringUtils.isEmpty(user.getUsername())){
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isEmpty(user.getPassword())){
            map.put("msg", "密码不能为空");
            return map;
        }

        map.put("msg", "success");
        return map;
    }

}
