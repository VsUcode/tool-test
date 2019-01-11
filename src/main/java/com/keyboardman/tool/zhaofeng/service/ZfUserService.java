package com.keyboardman.tool.zhaofeng.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.root.utils.RootUtils;
import com.keyboardman.tool.zhaofeng.dao.ZfLoginDao;
import com.keyboardman.tool.zhaofeng.dao.ZfUserDao;
import com.keyboardman.tool.zhaofeng.model.UserZF;
import com.keyboardman.tool.zhaofeng.utils.CommonZF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ZfUserService extends CommonFather {

    @Autowired
    private ZfLoginDao zfLoginDao;

    @Autowired
    private ZfUserDao zfUserDao;

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
        UserZF userZF = zfLoginDao.selectByName(username, RootConstant.STATUS_0);
        if (userZF != null){
            String pwd = password;
            pwd += CommonZF.MD5_SALT;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(userZF.getPassword())){
                // todo sql验证
                zfUserDao.deleteUser(userZF.getId(), RootConstant.STATUS_1);
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

        UserZF userZF = zfLoginDao.selectByName(username, RootConstant.STATUS_0);
        if (userZF != null){
            String pwd = password;
            pwd += CommonZF.MD5_SALT;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(userZF.getPassword())){
                // todo sql验证

                String newPwd = newPassword + CommonZF.MD5_SALT;
                newPwd = RootUtils.MD5(newPwd);

                zfUserDao.updatePassword(userZF.getId(), newPwd);
                map.put("msg", "success");
                return map;
            }
        }
        map.put("msg", "密码不正确，注销失败");
        return map;
    }
}
