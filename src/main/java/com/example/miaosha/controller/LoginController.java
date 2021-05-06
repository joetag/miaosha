package com.example.miaosha.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kelin
 */
@Controller
@RequestMapping("/login")
@Slf4j

public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
