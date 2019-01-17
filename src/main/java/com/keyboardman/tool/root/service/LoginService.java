package com.keyboardman.tool.root.service;

import com.keyboardman.tool.root.dao.LoginDao;
import com.keyboardman.tool.root.model.User;
import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.CommonMessage;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.root.utils.RootUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService extends CommonFather {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private CommonMessage commonMessage;
    /**
     * 注册
     * @param user
     * @return
     */
    public Map<String, String> register(User user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        super.validateEmpty("username", user.getUsername());
        super.validateEmpty("password", user.getPassword());

        User isUser = loginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (isUser != null){
            map.put("msg", "用户名已被注册");
            return map;
        }

        user.setPower(RootConstant.ROOT_USER_POWER);
        user.setStatus(RootConstant.STATUS_0);
        //密码强度
        String password = user.getPassword();
        password += RootConstant.ROOT_USER_POWER;
        String passwordResult= RootUtils.MD5(password);;
        user.setPassword(passwordResult);
        try{
            loginDao.addUser(user);
            map.put("msg", "success");
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public Map<String, String> login(User user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        super.validateEmpty("username", user.getUsername());
        super.validateEmpty("password", user.getPassword());

        User isUser = loginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (isUser != null){
            String pwd = user.getPassword();
            pwd += RootConstant.ROOT_USER_POWER;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(isUser.getPassword())){
                map.put("msg", "success");
            }else {
                map.put("msg", "登录失败，请检查用户名或密码");
            }

        }else {
            map.put("msg", "用户不存在");
        }

        return map;
    }

    /**
     * 根据username获取对象
     * @param username
     * @return
     */
    public User getUser(String username){
        super.validateEmpty("username", username);
        User user = loginDao.selectByName(username,RootConstant.STATUS_0);
        user.setPassword("");
        return user;
    }
}
