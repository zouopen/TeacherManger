package com.evlisay.schoolservices.service.Impl;

import com.evlisay.schoolservices.Enum.SchoolParameterEnum;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.exception.SchoolException;
import com.evlisay.schoolservices.repository.StudentInfoRepository;
import com.evlisay.schoolservices.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 18:21
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoRepository repository;

    @Override
    public StudentInfo findOne(Integer id) {
        if (id == null){
            throw new SchoolException(SchoolParameterEnum.ID_NULL);
        }
        return repository.findById(id).get();
    }

    @Override
    public void save(StudentInfo studentInfo) {
        if (studentInfo == null){
            throw new SchoolException(SchoolParameterEnum.INFO_NULL);
        }
        repository.save(studentInfo);
    }

    @Override
    public StudentInfo findByUsername(String username) {
        if (username == null){
            throw new SchoolException(SchoolParameterEnum.USERNAME_NULL);
        }
        return repository.findByUserName(username);
    }

    @Override
    public void deleteByUserName(String username) {

        repository.deleteByUserName(username);

    }

    @Override
    public StudentInfo findByCookie(String cookie) {
        return repository.findByStudentCookie(cookie);
    }
}
