package com.ethercat.user.service;

import com.ethercat.param.UserCheckParam;
import com.ethercat.utils.R;
import org.springframework.stereotype.Service;

public interface UserService {

    R check(UserCheckParam userCheckParam);
}
