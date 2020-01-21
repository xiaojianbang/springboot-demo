package com.panchina.springboot.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by lifei on 2020/1/21
 */
@Data
public class SystemUserParam {
    @NotNull(message = "id不能为空")
    private Long id ;

    @NotBlank(message = "username不能为空")
    private String username;

    @NotBlank(message = "username不能为空")
    private String password;

    @NotNull(message = "age不能为空")
    private Integer age;

}
