package com.evlisay.schoolservices.converter;

import com.evlisay.schoolservices.Form.SchoolLoginForm;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.service.SequenceService;
import com.evlisay.schoolservices.shiro.ShiroKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 19:03
 */
@Component
public class StudentInfo2SchoolLoginForm {
        @Autowired
        private SequenceService sequenceService;

        public StudentInfo convert(SchoolLoginForm schoolLoginForm, String name,String cookie) {
                StudentInfo studentInfo = new StudentInfo();

                BeanUtils.copyProperties(schoolLoginForm, studentInfo);

                studentInfo.setName(name);

                studentInfo.setSalt(ShiroKit.getRandomSalt(5));

                studentInfo.setPassword(ShiroKit.md5(schoolLoginForm.getPassword(), studentInfo.getSalt()));

                studentInfo.setStudentState(sequenceService.getSequenceId());

                studentInfo.setStudentCookie(cookie);

                return studentInfo;
        }
}
