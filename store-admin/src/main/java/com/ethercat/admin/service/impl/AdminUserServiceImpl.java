package com.ethercat.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.admin.mapper.AdminUserMapper;
import com.ethercat.admin.param.AdminUserParam;
import com.ethercat.admin.pojo.AdminUser;
import com.ethercat.admin.service.AdminUserService;
import com.ethercat.constants.UserConstants;
import com.ethercat.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-26 21:35
 **/

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 登录业务方法
     *
     * @param adminUserParam
     * @return
     */
    @Override
    public AdminUser login(AdminUserParam adminUserParam) {

        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",adminUserParam.getUserAccount());

        queryWrapper.eq("user_password", MD5Util.encode(adminUserParam.getUserAccount()+ UserConstants.USER_SLAT));

        AdminUser adminUser = adminUserMapper.selectOne(queryWrapper);

        log.info("AdminUserServiceImpl.login业务结束，结果：{}",adminUser);

        return adminUser;
    }
}
