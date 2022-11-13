package com.ethercat.user.service;

import com.ethercat.param.UserCheckParam;
import com.ethercat.pojo.User;
import com.ethercat.utils.R;
import org.springframework.stereotype.Service;

public interface UserService {

    R check(UserCheckParam userCheckParam);


    /**
     * 注册业务
     * @param user 参数已经校验，但是密码是明文
     * @return
     */
    R register(User user);
}
