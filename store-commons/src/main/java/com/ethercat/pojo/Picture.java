package com.ethercat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;

/**
 * @program: b2c-store
 * @description: 商品图片实体类
 * @author: Ethercat
 * @create: 2022-11-17 20:36
 **/
@Data
@TableName("product_picture")
public class Picture implements Serializable {

    public static final Long serialVersionUID = 1L  ;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("product_id")
    @JsonProperty("product_id")
    private Integer productId;
    @TableField("product_picture")
    @JsonProperty("product_picture")
    private String productPicture;

    private String intro;
}
