package com.evlisay.schoolservices.service.Impl;

import com.evlisay.schoolservices.constant.SchoolLoginAddress;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.dataObject.StudentPerson;
import com.evlisay.schoolservices.repository.StudentPersonRepository;
import com.evlisay.schoolservices.service.SequenceService;
import com.evlisay.schoolservices.service.StudentPersonService;
import com.evlisay.schoolservices.utils.HtmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 14:18
 */
@Service
@Slf4j
public class StudentPersonServiceImpl implements StudentPersonService {

    @Autowired
    private StudentPersonRepository personRepository;
    @Autowired
    private SequenceService sequenceService;

    /**
     *
     * @param type 指定的URL
     * @param state 指定的用户
     * @return
     */
    @Override
    public String returnUrl(String type,String state) {
        StudentPerson studentPerson = personRepository.findByStudentState(state);
        switch (type){
            case SchoolLoginAddress.PERSON_URL:
                return studentPerson.getPersonUrl();
            case SchoolLoginAddress.SCORE_URL:
                return studentPerson.getScoreUrl();
            case SchoolLoginAddress.CLASS_INFO_URL:
                return studentPerson.getClassUrl();
        }
        return null;
    }

    @Override
    public void save(StudentPerson studentPerson) {
        personRepository.save(studentPerson);
    }

    @Override
    public void create(StudentInfo studentInfo, String post) {

        StudentPerson studentPerson = new StudentPerson();

        studentPerson.setStudentState(studentInfo.getStudentState());

        studentPerson.setPerId(sequenceService.getSequenceId());

        studentPerson.setClassUrl(HtmlUtils.getClassPerson(post));

        studentPerson.setPersonUrl(HtmlUtils.getPersonUrl(post));

        studentPerson.setScoreUrl(HtmlUtils.getResultUrl(post));

        studentPerson.setPassUrl(HtmlUtils.getPassWordChanger(post));

        save(studentPerson);
    }
}
