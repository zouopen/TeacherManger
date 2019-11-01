package com.evlisay.schoolservices.service;

import com.evlisay.schoolservices.DTO.ShiroUserDTO;
import com.evlisay.schoolservices.vo.ResultVo;
import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoVo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 13:32
 * 权限认证
 */
public interface PowerService {

    ResultVo hasRole(String role, HttpSession session, ModelAndView modelAndView, ShiroUserDTO shiroUserDTO, StudentInfoVo studentInfoVo);
}
