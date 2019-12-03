package com.tujietg.template.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * @author tujietg
 * @date 2019/12/3 9:37 PM
 */
@Getter
@Setter
public class UmsAdminLoginParam {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;


}
