package com.evlisay.schoolservices.service.Impl;

import com.evlisay.schoolservices.DTO.ShiroUserDTO;
import com.evlisay.schoolservices.service.PowerService;
import com.evlisay.schoolservices.shiro.ShiroKit;
import com.evlisay.schoolservices.utils.ResultVOUtils;
import com.evlisay.schoolservices.vo.ResultVo;
import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 13:34
 */
@Service
public class PowerServiceImpl implements PowerService {
    @Override
    public ResultVo hasRole(String role, HttpSession session, ModelAndView modelAndView, ShiroUserDTO shiroUserDTO,StudentInfoVo studentInfoVo) {

        if (ShiroKit.getSubject().hasRole(role)){

            modelAndView.addObject("user", shiroUserDTO);

            session.setAttribute("user", shiroUserDTO);

            return ResultVOUtils.LoginSuccess();

        }else if (ShiroKit.getSubject().hasRole(role)){

            modelAndView.addObject("user", shiroUserDTO);

            session.setAttribute("user", shiroUserDTO);

            return ResultVOUtils.LoginSuccess();

        }else{

            modelAndView.addObject("user", shiroUserDTO);

            session.setAttribute("user", shiroUserDTO);

            return ResultVOUtils.success(studentInfoVo);

        }
    }
}
