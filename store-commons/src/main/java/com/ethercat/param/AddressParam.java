package com.ethercat.param;

import com.ethercat.pojo.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: b2c-store
 * @description: 地址接收值
 * @author: Ethercat
 * @create: 2022-11-26 14:53
 **/

@Data
public class AddressParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
    private Address add;
}
