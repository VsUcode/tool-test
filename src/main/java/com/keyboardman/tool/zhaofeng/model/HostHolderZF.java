package com.keyboardman.tool.zhaofeng.model;

import org.springframework.stereotype.Component;


@Component
public class HostHolderZF {
    private static ThreadLocal<UserZF> users = new ThreadLocal<>();

    public UserZF getUser(){
        return users.get();
    }

    public void setUsers(UserZF user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
