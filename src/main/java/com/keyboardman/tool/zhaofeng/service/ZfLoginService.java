package com.keyboardman.tool.zhaofeng.service;

import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.root.utils.RootUtils;
import com.keyboardman.tool.zhaofeng.dao.ZfLoginDao;
import com.keyboardman.tool.zhaofeng.model.UserZF;
import com.keyboardman.tool.zhaofeng.utils.CommonZF;
import com.keyboardman.tool.zhaofeng.utils.ZFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ZfLoginService {

    @Autowired
    private ZfLoginDao zfLoginDao;

    /**
     * 注册
     * @param user
     * @return
     */
    public Map<String, String> register(UserZF user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        map = ZFUtil.identifyUser(user);
        if (!map.get("msg").equals("success")){
            return map;
        }
        if (StringUtils.isEmpty(user.getPhone())){
            map.put("msg", "联系电话不能为空");
            return map;
        }

        UserZF isUser = zfLoginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (isUser != null){
            map.put("msg", "用户名已被注册");
            return map;
        }

        user.setPower(CommonZF.USER_POWER);
        user.setStatus(RootConstant.STATUS_0);
        //密码强度
        String password = user.getPassword();
        password += CommonZF.MD5_SALT;
        String passwordResult= null;
        passwordResult = RootUtils.MD5(password);
        user.setPassword(passwordResult);
        int flag = zfLoginDao.addUser(user);
        //todo 默认为登录
        if (flag == 1){
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public Map<String, String> login(UserZF user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        map = ZFUtil.identifyUser(user);
        if (!map.get("msg").equals("success")){
            return map;
        }

        UserZF userZF = zfLoginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (userZF != null){
            String pwd = user.getPassword();
            pwd += CommonZF.MD5_SALT;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(userZF.getPassword())){
                map.put("msg", "success");
                return map;
            }
            map.put("msg", "登录失败，请检查用户名或密码");
            return map;
        }

        map.put("msg", "用户不存在");
        return map;
    }

    /**
     * 根据username获取对象
     * @param username
     * @return
     */
    public UserZF getUser(String username){
        return zfLoginDao.selectByName(username, RootConstant.STATUS_0);
    }
}
