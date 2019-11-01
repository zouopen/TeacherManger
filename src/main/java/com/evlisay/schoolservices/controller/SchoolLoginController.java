package com.evlisay.schoolservices.controller;

import com.evlisay.schoolservices.Enum.SchoolLoginEnum;
import com.evlisay.schoolservices.Form.SchoolLoginForm;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.exception.SchoolException;
import com.evlisay.schoolservices.service.*;
import com.evlisay.schoolservices.shiro.ShiroKit;
import com.evlisay.schoolservices.utils.*;
import com.evlisay.schoolservices.vo.ResultVo;
import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 22:51
 */
@RequestMapping("/user")
@Slf4j
@RestController
public class SchoolLoginController {
    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResultVo schoolLogin(@Valid SchoolLoginForm schoolLoginForm,
                                BindingResult bindingResult) {
        StudentInfoVo studentInfoVo;

        StudentInfo service = studentInfoService.findByUsername(schoolLoginForm.getUsername());

        if (bindingResult.hasErrors()){
            log.error("[登录]登录参数不正确:"+Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            throw new SchoolException(SchoolLoginEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        UsernamePasswordToken token = new UsernamePasswordToken(schoolLoginForm.getUsername(), schoolLoginForm.getPassword().toCharArray());

        studentInfoVo = new StudentInfoVo();
        if (service != null){
            ShiroKit.getSubject().login(token);
            BeanUtils.copyProperties(service, studentInfoVo);
            loginService.quickLogon(schoolLoginForm);
        }else{
            //教务系统的认证
            loginService.quickLogon(schoolLoginForm);

            StudentInfo username = studentInfoService.findByUsername(schoolLoginForm.getUsername());

            BeanUtils.copyProperties(username, studentInfoVo);

            return ResultVOUtils.success(studentInfoVo);
        }

        //Shiro框架的认证
//        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) ShiroKit.getSubject().getPrincipal();
//        //权限认认证与控制跳转
//        powerService.hasRole(SchoolLoginEnum.ADMIN.getMessage(), session, modelAndView, shiroUserDTO, studentInfoVo);


        return ResultVOUtils.success(studentInfoVo);
    }
}
