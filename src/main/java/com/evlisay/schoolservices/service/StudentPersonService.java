package com.evlisay.schoolservices.service;

import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.dataObject.StudentPerson;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 14:18
 */
public interface StudentPersonService {

    String returnUrl(String type,String state);

    void save(StudentPerson studentPerson);

    void create(StudentInfo studentInfo, String post);
}
