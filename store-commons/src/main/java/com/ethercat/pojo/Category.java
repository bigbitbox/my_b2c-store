package com.ethercat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: b2c-store
 * @description: 类别实体类
 * @author: Ethercat
 * @create: 2022-11-16 19:50
 **/
@TableName("category")
@Data
public class Category {

    @JsonProperty("category_id")
    @TableId(type = IdType.AUTO)
    private Integer categoryId;
    @JsonProperty("category_name")
    private String categoryName;

}
