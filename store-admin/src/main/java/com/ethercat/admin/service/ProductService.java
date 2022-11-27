package com.ethercat.admin.service;

import com.ethercat.param.ProductSearchParam;
import com.ethercat.utils.R;

public interface ProductService {
    R search(ProductSearchParam productSearchParam);
}
