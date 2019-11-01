package com.evlisay.schoolservices.handler;

import com.evlisay.schoolservices.Enum.SchoolLoginEnum;

import com.evlisay.schoolservices.exception.SchoolException;
import com.evlisay.schoolservices.utils.ResultVOUtils;
import com.evlisay.schoolservices.vo.ResultVo;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.SocketTimeoutException;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 23:55
 */
@ControllerAdvice
public class SchoolExceptionHandler {




    @ExceptionHandler(value = SchoolException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultVo handlerExceptionSchool(SchoolException s){
        return ResultVOUtils.error(s.getMessage(),s.getCode());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultVo handlerAuthenticationException(){
        return ResultVOUtils.error(SchoolLoginEnum.LOGIN_ERROR.getMessage(),SchoolLoginEnum.LOGIN_ERROR.getCode());
    }

    @ExceptionHandler(value = SocketTimeoutException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultVo SocketTimeoutBlackUserException(){
        return ResultVOUtils.error(SchoolLoginEnum.LOGIN_NETWORK_ERROR.getMessage(),SchoolLoginEnum.LOGIN_NETWORK_ERROR.getCode());
    }
}
