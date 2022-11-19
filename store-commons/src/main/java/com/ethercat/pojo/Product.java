package com.ethercat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: b2c-store
 * @description: 商品实体类
 * @author: Ethercat
 * @create: 2022-11-16 19:47
 **/


@Data
@TableName("product")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("category_id")
    private String categoryId;

    /**
     * 手机title
     */
    @JsonProperty("product_title")
    private String productTitle;

    /**
     * 手机信息描述
     */
    @JsonProperty("product_intro")
    private String productIntro;

    @JsonProperty("product_picture")
    private String productPicture;

    /**
     * 商品价格
     */
    @JsonProperty("product_price")
    private Double productPrice;

    /**
     * 售卖价格
     */
    @JsonProperty("product_selling_price")
    private Double productSellingPrice;

    /**
     * 商品库存
     */
    @JsonProperty("product_num")
    private int productNum;

    /**
     * 已卖数量
     */
    @JsonProperty("product_sales")
    private int productSales;


}