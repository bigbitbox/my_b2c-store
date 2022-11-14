package com.ethercat.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: b2c-store
 * @description: 查询历史集合的接收
 * @author: Ethercat
 * @create: 2022-11-14 09:04
 **/

@Data
public class AddressListParam {


    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
