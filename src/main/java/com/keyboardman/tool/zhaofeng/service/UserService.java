package com.keyboardman.tool.zhaofeng.service;

import com.keyboardman.tool.zhaofeng.dao.LoginDao;
import com.keyboardman.tool.zhaofeng.dao.UserDao;
import com.keyboardman.tool.zhaofeng.model.UserZF;
import com.keyboardman.tool.zhaofeng.utils.CommonZF;
import com.keyboardman.tool.zhaofeng.utils.ZFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

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
        UserZF userZF = loginDao.selectByName(username, CommonZF.STATUS_0);
        if (userZF != null){
            String pwd = password;
            pwd += CommonZF.MD5_SALT;
            pwd = ZFUtil.MD5(pwd);

            if (pwd.equals(userZF.getPassword())){
                // todo sql验证
                userDao.deleteUser(userZF.getId(), CommonZF.STATUS_1);
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
        UserZF userZF = loginDao.selectByName(username, CommonZF.STATUS_0);
        if (userZF != null){
            String pwd = password;
            pwd += CommonZF.MD5_SALT;
            pwd = ZFUtil.MD5(pwd);

            if (pwd.equals(userZF.getPassword())){
                // todo sql验证

                String newPwd = newPassword + CommonZF.MD5_SALT;
                newPwd = ZFUtil.MD5(newPwd);

                userDao.updatePassword(userZF.getId(), newPwd);
                map.put("msg", "success");
                return map;
            }
        }
        map.put("msg", "密码不正确，注销失败");
        return map;
    }
}
