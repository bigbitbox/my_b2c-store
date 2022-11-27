package com.ethercat.admin.service.impl;

import com.ethercat.admin.service.UserService;
import com.ethercat.clients.UserClient;
import com.ethercat.param.CartListParam;
import com.ethercat.param.PageParam;
import com.ethercat.pojo.User;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-27 12:45
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    /**
     * 用户展示业务的方法
     *
     * @param pageParam
     * @return
     */
    @Cacheable(value = "list.user",key = "#pageParam.currentPage"+"-"+"#pageParam.pageSize")
    @Override
    public R userList(PageParam pageParam) {
        log.info("UserServiceImpl.userList业务结束，结果：{}",pageParam);
        R r = userClient.adminListPage(pageParam);
        log.info("UserServiceImpl.userList业务结束，结果：{}",r);
        return r;
    }

    /**
     * 删除用户数据
     *
     * @param cartListParam
     * @return
     */

    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userRemove(CartListParam cartListParam) {
        R r = userClient.adminRemove(cartListParam);
        log.info("UserServiceImpl.userRemove业务结束，结果：{}",r);

        return r;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userUpdate(User user) {
        R r = userClient.adminUpdate(user);
        log.info("UserServiceImpl.userUpdate业务结束，结果：{}",r);

        return r;
    }

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userSave(User user) {
        R r = userClient.adminSave(user);
        log.info("UserServiceImpl.userSave业务结束，结果：{}",r);
        return r;
    }
}
