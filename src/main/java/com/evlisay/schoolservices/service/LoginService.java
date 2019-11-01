package com.evlisay.schoolservices.service;

import com.evlisay.schoolservices.Form.SchoolLoginForm;

/**
 * @Author: EvilSay
 * @Date: 2019/8/29 22:51
 */
public interface LoginService {

    String quickLogon(SchoolLoginForm schoolLoginForm);

    void login(SchoolLoginForm schoolLoginForm);
}
