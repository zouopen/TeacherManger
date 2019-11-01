package com.evlisay.schoolservices.repository;

import com.evlisay.schoolservices.dataObject.StudentUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 21:10
 */
public interface StudentUserRoleServiceRepository extends JpaRepository<StudentUserRole,Integer> {

    StudentUserRole findByUserId (Integer id);
}
