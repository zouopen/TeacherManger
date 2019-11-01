package com.evlisay.schoolservices.repository;

import com.evlisay.schoolservices.dataObject.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 18:11
 */
public interface StudentInfoRepository extends JpaRepository<StudentInfo,Integer> {

    StudentInfo findByUserName (String username);

    @Transactional
    void deleteByUserName(String username);

    StudentInfo findByStudentCookie(String cookie);
}
