package com.evlisay.schoolservices.service;

import com.evlisay.schoolservices.dataObject.StudentUserRole;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 21:08
 */
public interface StudentUserRoleService {

    StudentUserRole findOne(Integer id);

    StudentUserRole findUserId(Integer id);

    void save(StudentUserRole studentUserRole);

}
