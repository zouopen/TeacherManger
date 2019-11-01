package com.evlisay.schoolservices.service.Impl;

import com.evlisay.schoolservices.dataObject.StudentRole;
import com.evlisay.schoolservices.repository.StudentRoleRepository;
import com.evlisay.schoolservices.service.StudentRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 20:59
 */
@Service
public class StudentRoleServiceImpl implements StudentRoleService {

    @Autowired
    private StudentRoleRepository studentRoleRepository;

    @Override
    public StudentRole findOne(Integer id) {
        return studentRoleRepository.findById(id).get();
    }
}
