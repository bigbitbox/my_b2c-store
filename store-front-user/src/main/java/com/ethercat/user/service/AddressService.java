package com.ethercat.user.service;

import com.ethercat.pojo.Address;
import com.ethercat.utils.R;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-14 16:35
 **/

public interface AddressService {

    /**
     * 根据用户id查询 地址数据
     * @param userId 用户id已经校验完毕
     * @return 001 004
     */
    R list(Integer userId);

    R save(Address address);

    R remove(Integer id);
}
