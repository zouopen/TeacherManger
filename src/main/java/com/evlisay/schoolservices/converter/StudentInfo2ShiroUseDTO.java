package com.evlisay.schoolservices.converter;

import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.dataObject.StudentUserRole;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 21:56
 */
public class StudentInfo2ShiroUseDTO {
    /**
     * 生成角色权限值
     * @param studentInfo
     * @return
     */
    public static StudentUserRole converter(StudentInfo studentInfo){

        StudentUserRole studentUserRole = new StudentUserRole();

        studentUserRole.setUserId(studentInfo.getId());

        studentUserRole.setRoleId(3);

        return studentUserRole;
    }
}
