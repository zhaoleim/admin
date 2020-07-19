package com.admin.springboot.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class MainController {
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login() {
        System.out.println("用户注册！");
        return "sucess";
    }

}
