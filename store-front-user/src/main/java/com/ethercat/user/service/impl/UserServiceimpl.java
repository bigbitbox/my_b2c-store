package com.ethercat.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethercat.constants.UserConstants;
import com.ethercat.param.CartListParam;
import com.ethercat.param.PageParam;
import com.ethercat.param.UserCheckParam;
import com.ethercat.param.UserLoginParam;
import com.ethercat.pojo.User;
import com.ethercat.user.service.UserService;
import com.ethercat.user.mapper.UserMapper;
import com.ethercat.utils.MD5Util;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: b2c-store
 * @description: 1
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
            log.info("UserServiceimpl.register业务结束，结果：{}","账号已经存在，不可注册");
            return R.fail("账号已经存在，不可注册");
        }

//        2.密码加密处理，要加盐处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

//        3.insert database
        int rows = userMapper.insert(user);

        if(rows == 0){
            log.info("UserServiceimpl.register业务结束，结果：{数据插入失败，注册失败}");
            return R.fail("注册失败！请稍后再试");
        }

        log.info("UserServiceimpl.register业务结束，结果：{注册成功}");
        return R.ok("注册成功！");
    }


    /**
     * 登录业务
     *   1. 密码的加密和加盐处理
     *   2. 账号和密码进行数据库查询.返回一个完整的数据库user对象
     *   3. 判断返回结果
     * @param userLoginParam 账号和密码 已经校验 但是密码是明文!
     * @return 结果 001 004
     */
    @Override
    public R login(UserLoginParam userLoginParam) {
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userLoginParam.getUserName());
        queryWrapper.eq("password",newPwd);

        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            log.info("UserServiceimpl.login业务结束，结果：{账号密码错误}");
            return R.fail("账号密码错误");
        }

        log.info("UserServiceimpl.login业务结束，结果：{登录成功}");
        user.setPassword(null);
        return R.ok("登录成功",user);
    }

    @Override
    public R listPage(PageParam pageParam) {

        IPage<User> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());

        page = userMapper.selectPage(page, null);

        List<User> records = page.getRecords();
        long total = page.getTotal();

        return R.ok("用户管理查询成功！",records,total);
    }

    /**
     * 根据用户id删除数据
     *
     * @param cartListParam
     * @return
     */
    @Override
    public R remove(CartListParam cartListParam) {

        int i = userMapper.deleteById(cartListParam.getUserId());

        log.info("UserServiceimpl.remove业务结束，结果：{}",i);

        return R.ok("用户数据删除成功！");
    }
}
