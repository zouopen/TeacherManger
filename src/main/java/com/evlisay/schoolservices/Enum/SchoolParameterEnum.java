package com.evlisay.schoolservices.Enum;

import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 18:28
 */
@Getter
public enum  SchoolParameterEnum {
    ID_NULL(101,"ID参数为空!"),
    INFO_NULL(102,"保存信息为空！"),
    USERNAME_NULL(103,"保存信息为空"),
    ;

    private Integer code;

    private String  message;

    SchoolParameterEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
