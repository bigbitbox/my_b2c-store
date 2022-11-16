package com.ethercat.clients;

import com.ethercat.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("category-service")
public interface CategoryClient {

    @GetMapping("/category/promo/{categoryName}")
    R byName(@PathVariable String categoryName);
}
