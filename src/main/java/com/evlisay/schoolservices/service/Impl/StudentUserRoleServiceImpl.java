package com.evlisay.schoolservices.service.Impl;

import com.evlisay.schoolservices.dataObject.StudentUserRole;
import com.evlisay.schoolservices.repository.StudentUserRoleServiceRepository;
import com.evlisay.schoolservices.service.StudentUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 21:09
 */
@Service
public class StudentUserRoleServiceImpl implements StudentUserRoleService {
    @Autowired
    private StudentUserRoleServiceRepository studentUserRoleServiceRepository;

    @Override
    public StudentUserRole findOne(Integer id) {
        return studentUserRoleServiceRepository.findById(id).get();
    }

    @Override
    public StudentUserRole findUserId(Integer id) {
        return studentUserRoleServiceRepository.findByUserId(id);
    }

    @Override
    public void save(StudentUserRole studentUserRole) {
        studentUserRoleServiceRepository.save(studentUserRole);
    }

}
