package com.evlisay.schoolservices.Enum;

import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 23:28
 */
@Getter
public enum  SchoolLoginEnum {
    ERROR(-1,"登录失败"),
    ADMIN(1,"admin"),
    LOGIN_PARAM_ERROR(3,"登录参数不正确"),
    LOGIN_CHECK_CODE(4661,"验证码错误"),
    LOGIN_ERROR(4673,"账号密码错误"),
    LOGIN_SUCCESS(10000,"登录失败"),
    LOGIN_NETWORK_ERROR(-1001,"学院网络异常"),
    LOGIN_ERROR_UN(-2,"您好像没有登录哦"),
    GET_INFO_DATA_ERROR(222,"请求失败,服务器Cookie失效.请重新登录！"),
    GET_INFO_NO_DATA_ERROR(849,"请求失败,服务器Cookie失效.请重新登录！"),
    TWO_CHECK_LOGIN_ERROR(849,"快速登录失败！账号密码错误"),
    LOGIN_LOCK(4692,"账号已被锁定。请联系管理员！"),
    LOGIN_QUICK_LOGON(4782,"快速登录已经取消，密码错误4次将锁定账号！"),
    LOGIN_ERROR_MORE(4982,"提交的太快啦,请休息一下吧"),
    ;

    private Integer code;

    private String message;

    SchoolLoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
