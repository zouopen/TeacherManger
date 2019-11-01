package com.evlisay.schoolservices.repository;

import com.evlisay.schoolservices.dataObject.StudentPerson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 14:12
 */
public interface StudentPersonRepository extends JpaRepository<StudentPerson,String> {

    StudentPerson findByStudentState (String StudentState);

}
