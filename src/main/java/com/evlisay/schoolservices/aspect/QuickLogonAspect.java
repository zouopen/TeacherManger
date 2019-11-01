package com.evlisay.schoolservices.aspect;

import com.evlisay.schoolservices.DTO.ShiroUserDTO;
import com.evlisay.schoolservices.Enum.SchoolLoginEnum;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.exception.SchoolException;
import com.evlisay.schoolservices.service.StudentInfoService;
import com.evlisay.schoolservices.shiro.ShiroKit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: EvilSay
 * @Date: 2019/7/15 19:43
 * 快速登录错误次数校验
 */
@Aspect
@Component
@Slf4j
public class QuickLogonAspect {

    @Autowired
    private StudentInfoService studentInfoService;

    @Pointcut("execution(public * com.evlisay.schoolservices.controller.SchoolStudentController.rePassword(..))")
    public void logonCheck(){

    }

    @Before("logonCheck()")
    public void check(){

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = servletRequestAttributes.getRequest();

        ShiroUserDTO shiroUser = (ShiroUserDTO) request.getUserPrincipal();

        StudentInfo studentInfo = studentInfoService.findByUsername(shiroUser.getUserName());

        if (studentInfo.getQuickLogon() == 3){
            ShiroKit.getSubject().logout();
            studentInfo.setQuickLogon(0);
            studentInfoService.save(studentInfo);
            throw new SchoolException(SchoolLoginEnum.LOGIN_QUICK_LOGON);
        }
    }
}
