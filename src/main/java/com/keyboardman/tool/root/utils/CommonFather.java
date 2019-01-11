package com.keyboardman.tool.root.utils;

import org.springframework.util.StringUtils;

public class CommonFather {

    protected void validateEmpty(String name, Object val){
        if(StringUtils.isEmpty(val)){
            throw new RuntimeException("【"+name+"】不能为空");
        }
    }
}
