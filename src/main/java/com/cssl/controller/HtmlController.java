package com.cssl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {
    /*
    * 登录页面跳转
    * */
    @GetMapping("login")
    public String login(){
        System.out.println("版本冲突测试第一次");
        System.out.println("进入登录面");
        return "login";
    }
    /*
    * 注册页面跳转
    * */
    @GetMapping("regist")
    public String regist(){
        return "regist";
    }
    @RequestMapping("/loginout")
    public String loginOut(){
        return "loginout";
    }
}
