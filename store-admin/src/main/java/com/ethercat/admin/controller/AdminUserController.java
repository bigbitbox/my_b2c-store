package com.ethercat.admin.controller;

import com.ethercat.admin.param.AdminUserParam;
import com.ethercat.admin.pojo.AdminUser;
import com.ethercat.admin.service.AdminUserService;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: b2c-store
 * @description: 后台管理用户处理controller
 * @author: Ethercat
 * @create: 2022-11-26 18:56
 **/

@Slf4j
@RestController
@RequestMapping
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/user/login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session){
        if (result.hasErrors()) {
            return R.fail("核心参数null，登录失败！");
        }

        //验证码校验
        String captcha =(String) session.getAttribute("captcha");
        String verCode = adminUserParam.getVerCode();

        if (!verCode.equalsIgnoreCase(captcha)){
            log.info("AdminUserController.login业务结束，结果：{}",captcha);
            log.info("AdminUserController.login业务结束，结果：{}",verCode);
            return R.fail("验证码错误！");
        }

        AdminUser user = adminUserService.login(adminUserParam);

        if (user == null) {
            return R.fail("登录失败！账号或密码错误！");
        }

        session.setAttribute("userInfo",user);

        return R.ok("登录成功");
    }

    @GetMapping("user/logout")
    public R logout(HttpSession session){
        //清空session即可
        session.invalidate();

        return R.ok("退出登录成功！");
    }
}
