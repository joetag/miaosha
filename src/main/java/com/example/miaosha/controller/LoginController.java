package com.example.miaosha.controller;

import com.example.miaosha.service.SkUserService;
import com.example.miaosha.vo.LoginVo;
import com.example.miaosha.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kelin
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Resource
    SkUserService skUserService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Validated LoginVo loginVo, HttpServletResponse response, HttpServletRequest request) {
        log.info("{}", loginVo);
        return skUserService.doLogin(loginVo, response, request);
    }
}
