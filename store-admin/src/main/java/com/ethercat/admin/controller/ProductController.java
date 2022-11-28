package com.ethercat.admin.controller;

import com.ethercat.admin.service.ProductService;
import com.ethercat.admin.utils.AliyunOSSUtils;
import com.ethercat.param.ProductSearchParam;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-28 00:09
 **/


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @GetMapping("list")
    public R adminList(ProductSearchParam productSearchParam){
        return productService.search(productSearchParam);

    }

    @PostMapping("upload")
    public R adminUpload(@RequestParam("img") MultipartFile img) throws Exception {
        String originalFilename = img.getOriginalFilename();
        originalFilename = UUID.randomUUID().toString().replaceAll("-","")
                            + originalFilename;

        String contentType = img.getContentType();
        byte[] bytes = img.getBytes();

        int hours = 1000;

        String url = aliyunOSSUtils.uploadImage(originalFilename, bytes, contentType, hours);
        System.out.println("url = " + url);
        return R.ok("图片上传成功！",url);
    }
}
