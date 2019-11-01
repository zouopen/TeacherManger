package com.evlisay.schoolservices.Form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 23:15
 */
@Data
public class SchoolLoginForm {


    @NotEmpty(message = "登录的账号不能为空")
    private String username;

    @NotEmpty(message = "登录的密码不能为空")
    private String password;

    private String checkCode;

    private String RadioButtonList1 = "学生";

    private String Button1 = " ";
    //viewState值一般为固定值
    private String __VIEWSTATE = "dDw3OTkxMjIwNTU7Oz5qFv56B08dbR82AMSOW+P8WDKexA==";
}
