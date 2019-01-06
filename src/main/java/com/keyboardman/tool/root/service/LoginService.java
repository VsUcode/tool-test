package com.keyboardman.tool.root.service;

import com.keyboardman.tool.root.dao.LoginDao;
import com.keyboardman.tool.root.model.User;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.root.utils.RootUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    /**
     * 注册
     * @param user
     * @return
     */
    public Map<String, String> register(User user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        // TODO 非空验证
//        map = ZFUtil.identifyUser(user);
        if (!map.get("msg").equals("success")){
            return map;
        }
//        if (StringUtils.isEmpty(user.getPhone())){
//            map.put("msg", "联系电话不能为空");
//            return map;
//        }

        User isUser = loginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (isUser != null){
            map.put("msg", "用户名已被注册");
            return map;
        }

        user.setPower(RootConstant.ROOT_ZF_USER_POWER);
        user.setStatus(RootConstant.STATUS_0);
        //密码强度
        String password = user.getPassword();
        password += RootConstant.ROOT_ZF_USER_POWER;
        String passwordResult= null;
        passwordResult = RootUtils.MD5(password);
        user.setPassword(passwordResult);
        int flag = loginDao.addUser(user);
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
    public Map<String, String> login(User user) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        // TODO 非空验证

        User isUser = loginDao.selectByName(user.getUsername(), RootConstant.STATUS_0);
        if (isUser != null){
            String pwd = user.getPassword();
            pwd += RootConstant.ROOT_ZF_USER_POWER;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(isUser.getPassword())){
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
    public User getUser(String username){
        return loginDao.selectByName(username,RootConstant.STATUS_0);
    }
}
