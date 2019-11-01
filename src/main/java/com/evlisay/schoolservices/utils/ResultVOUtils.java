package com.evlisay.schoolservices.utils;

import com.evlisay.schoolservices.vo.ResultVo;


/**
 * @Author: EvilSay
 * @Date: 2019/7/8 22:59
 */
public class ResultVOUtils {
    public static ResultVo<Object> success(Object object){
        ResultVo<Object> resultVo  = new ResultVo<>();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMessage("成功");
        return resultVo;
    }

    public static ResultVo<Object> error(String message,Integer code){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        return resultVo;
    }

    public static ResultVo<Object> LoginSuccess(){
        ResultVo<Object> resultVo  = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMessage("登录成功");
        return resultVo;
    }
}
