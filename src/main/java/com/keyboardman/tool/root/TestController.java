package com.keyboardman.tool.root;


import com.keyboardman.tool.root.utils.LocalLock;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * 重复提交
     * @return
     */
    @LocalLock(key = "test:arg[0]")
    @RequestMapping("/test")
    public String getString(){
        return "hello world";
    }
}
