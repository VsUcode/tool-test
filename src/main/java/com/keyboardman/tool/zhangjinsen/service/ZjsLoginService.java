package com.keyboardman.tool.zhangjinsen.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.root.utils.RootUtils;
import com.keyboardman.tool.zhangjinsen.dao.ZjsLoginDao;
import com.keyboardman.tool.zhangjinsen.model.UserZJS;
import com.keyboardman.tool.zhangjinsen.utils.CommonZJS;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ZjsLoginService extends CommonFather {

    @Autowired
    private ZjsLoginDao zjsLoginDao;

    /**
     * 注册
     * @param user
     * @return
     */
    public Map<String, String> register(UserZJS user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        super.validateEmpty("username", user.getUsername());
        super.validateEmpty("password", user.getPassword());
        super.validateEmpty("phone", user.getPhone());
        super.validateEmpty("power", user.getPower());

        UserZJS isUser = zjsLoginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (isUser != null){
            map.put("msg", "用户名已被注册");
            return map;
        }

        user.setStatus(RootConstant.STATUS_0);
        //密码强度
        String password = user.getPassword();
        password += CommonZJS.MD5_SALT;
        String passwordResult= RootUtils.MD5(password);
        user.setPassword(passwordResult);
        int flag = zjsLoginDao.addUser(user);
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
    public Map<String, String> login(UserZJS user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        super.validateEmpty("username", user.getUsername());
        super.validateEmpty("password", user.getPassword());

        UserZJS userZJS = zjsLoginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (userZJS != null){
            String pwd = user.getPassword();
            pwd += CommonZJS.MD5_SALT;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(userZJS.getPassword())){
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
    public UserZJS getUser(String username){
        super.validateEmpty("username", username);

        UserZJS userZJS = zjsLoginDao.selectByName(username, RootConstant.STATUS_0);
        userZJS.setPassword("");
        return userZJS;
    }
}
