package com.ethercat.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.constants.UserConstants;
import com.ethercat.param.UserCheckParam;
import com.ethercat.pojo.User;
import com.ethercat.user.service.UserService;
import com.ethercat.user.mapper.UserMapper;
import com.ethercat.utils.MD5Util;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-13 08:35
 **/

@Slf4j
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public R check(UserCheckParam userCheckParam) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_name",userCheckParam.getUserName());

        Long total = userMapper.selectCount(queryWrapper );

        if (total == 0) {
            log.info("UserServiceimpl.check业务结束，结果：{}","账号可以使用");
            return R.ok("账号不存在，可以使用");
        }


        return R.fail("账号已经存在不可注册");
    }

    @Override
    public R register(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_name",user.getUserName());

        Long total = userMapper.selectCount(queryWrapper );

        if(total > 0 ){
            log.info("UserServiceimpl.register业务结束，结果：{}","账号已经存在，不可注册")
            return R.fail("账号已经存在，不可注册");
        }

//        2.密码加密处理，要加盐处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

//        3.insert database
        int rows = userMapper.insert(user);

        if(rows == 0){
            log.info("UserServiceimpl.register业务结束，结果：{数据插入失败，注册失败}")
            return R.fail("注册失败！请稍后再试");
        }

        log.info("UserServiceimpl.register业务结束，结果：{注册成功}")
        return R.ok("注册成功！");


        return null;
    }
}
