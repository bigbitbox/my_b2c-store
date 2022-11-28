package com.ethercat.admin.service;

import com.ethercat.param.PageParam;
import com.ethercat.utils.R;

public interface OrderService {
    R list(PageParam pageParam);
}
