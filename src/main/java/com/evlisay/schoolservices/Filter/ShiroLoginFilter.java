package com.evlisay.schoolservices.Filter;

import com.evlisay.schoolservices.Enum.SchoolLoginEnum;
import com.evlisay.schoolservices.vo.ResultVo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 23:56
 * 请求判断
 *
 */
public class ShiroLoginFilter extends FormAuthenticationFilter {
    /**
     * 在访问controller前判断登录.返回Json不进行重定向
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Gson gson = new Gson();

        httpServletResponse.setCharacterEncoding("UTF-8");

        httpServletResponse.setContentType("application/json");

        ResultVo resultVo = new ResultVo();

        resultVo.setCode(SchoolLoginEnum.LOGIN_ERROR_UN.getCode());

        resultVo.setMessage(SchoolLoginEnum.LOGIN_ERROR_UN.getMessage());

        httpServletResponse.getWriter().write(gson.toJson(resultVo));

        return false;
    }
    //TODO Ajax判断
    private boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
