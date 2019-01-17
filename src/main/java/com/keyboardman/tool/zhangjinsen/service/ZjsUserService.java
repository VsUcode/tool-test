package com.keyboardman.tool.zhangjinsen.service;

import com.keyboardman.tool.root.utils.CommonFather;
import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.root.utils.RootUtils;
import com.keyboardman.tool.zhangjinsen.dao.ZjsLoginDao;
import com.keyboardman.tool.zhangjinsen.dao.ZjsUserDao;
import com.keyboardman.tool.zhangjinsen.model.UserZJS;
import com.keyboardman.tool.zhangjinsen.utils.CommonZJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ZjsUserService extends CommonFather {

    @Autowired
    private ZjsUserDao zjsUserDao;

    @Autowired
    private ZjsLoginDao zjsLoginDao;

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
        UserZJS userZJS = zjsLoginDao.selectByName(username, RootConstant.STATUS_0);
        if (userZJS != null){
            String pwd = password;
            pwd += CommonZJS.MD5_SALT;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(userZJS.getPassword())){
                try{
                    zjsUserDao.deleteUser(userZJS.getId(), RootConstant.STATUS_1);
                    map.put("msg", "success");
                }catch (Exception e){
                    map.put("msg", "数据库错误");
                    e.printStackTrace();
                }

            }else{
                map.put("msg", "密码不正确，注销失败");
            }
        }else {
            map.put("msg", "用户不存在");
        }

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

        UserZJS userZJS = zjsLoginDao.selectByName(username, RootConstant.STATUS_0);
        if (userZJS != null){
            String pwd = password;
            pwd += CommonZJS.MD5_SALT;
            pwd = RootUtils.MD5(pwd);

            if (pwd.equals(userZJS.getPassword())){
                String newPwd = newPassword + CommonZJS.MD5_SALT;
                newPwd = RootUtils.MD5(newPwd);
                try{
                    zjsUserDao.updatePassword(userZJS.getId(), newPwd);
                    map.put("msg", "success");
                }catch (Exception e){
                    map.put("msg", "数据库错误");
                    e.printStackTrace();
                }
            }else {
                map.put("msg", "密码不正确，更改失败");
            }
        }else{
            map.put("msg","用户不存在");
        }

        return map;
    }

    /**
     * 更改电话
     * @param username
     * @param phone
     * @return
     */
    public Map<String, String> changePhone(String username, String phone) {
        Map<String, String> map = new HashMap<>();
        super.validateEmpty("username", username);
        super.validateEmpty("phone", phone);

        UserZJS userZJS = zjsLoginDao.selectByName(username, RootConstant.STATUS_0);
        if (userZJS != null){
            zjsUserDao.updatePhone(userZJS.getId(), phone);

            UserZJS user = zjsLoginDao.selectByName(username, RootConstant.STATUS_0);
            if (user.getPhone().equals(phone)){
                map.put("msg", "success");
                return map;
            }
        }

        map.put("msg", "fail");
        return map;
    }
}
