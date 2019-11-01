package com.evlisay.schoolservices.service;

import com.evlisay.schoolservices.dataObject.StudentInfo;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 18:16
 */
public interface StudentInfoService {

    StudentInfo findOne(Integer id);

    void save(StudentInfo studentInfo);

    StudentInfo findByUsername(String username);

    void deleteByUserName(String username);

    StudentInfo findByCookie(String cookie);
}
