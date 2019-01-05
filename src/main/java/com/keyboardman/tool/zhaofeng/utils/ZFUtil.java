package com.keyboardman.tool.zhaofeng.utils;

import com.keyboardman.tool.zhaofeng.model.UserZF;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    /**
     * MD5j加密
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String MD5(String password) throws UnsupportedEncodingException {
        MessageDigest md5 = null;
        String passwordResult = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            passwordResult= base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return passwordResult;
    }
}
