package com.evlisay.schoolservices.DTO;

import lombok.Data;

import java.io.Serializable;
import java.security.Principal;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 20:41
 */
@Data
public class ShiroUserDTO implements Principal,Serializable {

    //账号名称
    private String userName;
    //账号编号
    private String studentState;
    //账号权限值
    private String userRole;

    @Override
    public String getName() { return null; }
}
