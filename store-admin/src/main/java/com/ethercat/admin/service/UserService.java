package com.ethercat.admin.service;

import com.ethercat.param.CartListParam;
import com.ethercat.param.PageParam;
import com.ethercat.pojo.User;
import com.ethercat.utils.R;

public interface UserService {

    /**
     * 用户展示业务的方法
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     * @param cartListParam
     * @return
     */
    R userRemove(CartListParam cartListParam);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R userUpdate(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R userSave(User user);
}
