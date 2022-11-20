package com.ethercat.collect.service;

import com.ethercat.pojo.Collect;
import com.ethercat.utils.R;

public interface CollectService {
    /**
     * 保存搜藏的方法
     * @param collect
     * @return 001  004
     */
    R save(Collect collect);

    /**
     * 根据用户id查询商品信息集合
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 删除保存的方法
     * @param collect
     * @return
     */

    R remove(Collect collect);
}
