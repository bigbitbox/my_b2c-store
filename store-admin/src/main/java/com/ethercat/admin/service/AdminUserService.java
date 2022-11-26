package com.ethercat.admin.service;

import com.ethercat.admin.param.AdminUserParam;
import com.ethercat.admin.pojo.AdminUser;

public interface AdminUserService {


    /**
     * 登录业务方法
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);
}
