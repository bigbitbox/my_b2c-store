package com.ethercat.admin.service;

import com.ethercat.param.ProductSaveParam;
import com.ethercat.param.ProductSearchParam;
import com.ethercat.pojo.Product;
import com.ethercat.utils.R;

public interface ProductService {
    R search(ProductSearchParam productSearchParam);

    R save(ProductSaveParam productSaveParam);

    R update(Product product);
}
