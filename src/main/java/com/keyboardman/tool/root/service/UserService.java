package com.keyboardman.tool.root.service;

import com.keyboardman.tool.root.dao.LoginDao;
import com.keyboardman.tool.root.dao.UserDao;
import com.keyboardman.tool.root.model.User;
import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.root.utils.RootUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends CommonFather {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UserDao userDao;

    /**
     * 注销用户
     * @param username
     * @param password
     * @return
     */
    public Map<String, String> deleteUser(String username, String password) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        super.validateEmpty("username", username);
        super.validateEmpty("password", password);
        User user = loginDao.selectByName(username, RootConstant.STATUS_0);
        if (user != null){
            String pwd = password;
            pwd += RootConstant.ROOT_USER_POWER;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(user.getPassword())){
                // todo sql验证
                userDao.deleteUser(user.getId(), RootConstant.STATUS_1);
                map.put("msg", "success");
                return map;
            }
        }
        map.put("msg", "密码不正确，注销失败");
        return map;
    }

    /**
     * 更改密码
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    public Map<String, String> changePwd(String username, String password, String newPassword) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        super.validateEmpty("username", username);
        super.validateEmpty("password", password);
        super.validateEmpty("newPassword", newPassword);
        User user = loginDao.selectByName(username, RootConstant.STATUS_0);
        if (user != null){
            String pwd = password;
            pwd += RootConstant.ROOT_USER_POWER;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(user.getPassword())){
                // todo sql验证

                String newPwd = newPassword + RootConstant.ROOT_USER_POWER;
                newPwd = RootUtils.MD5(newPwd);

                userDao.updatePassword(user.getId(), newPwd);
                map.put("msg", "success");
                return map;
            }
        }
        map.put("msg", "密码不正确，注销失败");
        return map;
    }
}
