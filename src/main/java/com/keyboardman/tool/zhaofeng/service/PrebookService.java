package com.keyboardman.tool.zhaofeng.service;

import com.keyboardman.tool.root.utils.RootConstant;
import com.keyboardman.tool.zhaofeng.dao.PrebookDao;
import com.keyboardman.tool.zhaofeng.model.PrebookZF;
import com.keyboardman.tool.zhaofeng.utils.CommonZF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrebookService {

    @Autowired
    private PrebookDao prebookDao;

    /**
     * 查询自己预约
     * @param username
     * @return
     */
    public PrebookZF selectBook(String username) {
        // todo 非空判断

        PrebookZF prebookZF = prebookDao.selectByUsername(username, RootConstant.STATUS_0);

        return prebookZF;
    }

    /**
     * 添加预约
     * @param entity
     * @return
     */
    public Map<String, String> addBook(PrebookZF entity) {
        Map<String, String> map = new HashMap<>();
        // todo 非空判断

        // todo 时间段排查 提高交互性

        PrebookZF prebookZF = prebookDao.selectByUsername(entity.getUsername(), RootConstant.STATUS_0);
        if (prebookZF != null){
            map.put("msg", "已预约");
            return map;
        }
        entity.setStatus(RootConstant.STATUS_0);
        int flag = prebookDao.insertBook(entity);
        if (flag == 1){
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * 删除预约
     * @param username
     * @return
     */
    public Map<String, String> deleteBook(String username) {
        Map<String, String> map = new HashMap<>();
        // todo 非空判断
        PrebookZF prebookZF = prebookDao.selectByUsername(username, RootConstant.STATUS_0);
        int flag = prebookDao.deleteByid(prebookZF.getId(), RootConstant.STATUS_1);
        if (flag == 1){
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * 管理员根据type查看预约列表
     * @param type
     * @return
     */
    public List<PrebookZF> selectBookList(String type) {
        //todo 非空校验

        Date now = new Date();

        List<PrebookZF> list = prebookDao.selectBookList(type, RootConstant.STATUS_0, now);

        return list;
    }
}
