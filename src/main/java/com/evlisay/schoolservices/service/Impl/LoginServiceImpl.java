package com.evlisay.schoolservices.service.Impl;

import com.evlisay.schoolservices.Enum.SchoolLoginEnum;
import com.evlisay.schoolservices.Form.SchoolLoginForm;
import com.evlisay.schoolservices.constant.SchoolLoginAddress;
import com.evlisay.schoolservices.converter.StudentInfo2SchoolLoginForm;
import com.evlisay.schoolservices.converter.StudentInfo2ShiroUseDTO;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.exception.SchoolException;
import com.evlisay.schoolservices.service.LoginService;
import com.evlisay.schoolservices.service.StudentInfoService;
import com.evlisay.schoolservices.service.StudentPersonService;
import com.evlisay.schoolservices.service.StudentUserRoleService;
import com.evlisay.schoolservices.utils.HtmlUtils;
import com.evlisay.schoolservices.utils.OkHttpUtils;
import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoVo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @Author: EvilSay
 * @Date: 2019/8/29 22:59
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private OkHttpUtils okHttpUtils;
    @Autowired
    private StudentPersonService studentPersonService;
    @Autowired
    private StudentUserRoleService studentUserRoleService;
    @Autowired
    private StudentInfo2SchoolLoginForm studentInfo2SchoolLoginForm;
    @Autowired
    private StudentInfoService studentInfoService;

    @Override
    public String quickLogon(SchoolLoginForm schoolLoginForm) {
        //获取快速登录的Cookie
        Response response = okHttpUtils.getCookie(SchoolLoginAddress.TWO_CHECK_LOGIN);

        String set_cookie = response.networkResponse().header("Set-Cookie");

        StringTokenizer stringTokenizer = new StringTokenizer(set_cookie,";");

        String cookie = stringTokenizer.nextToken();

        //获取快速登录的VIEWSTATE
        try {
            String string = response.body().string();
            String viewState = Jsoup.parse(string).select("input[name=__VIEWSTATE]").attr("value");
            //登录验证操作
            Map<String,String> map = new HashMap<>();
            map.put("__VIEWSTATE",viewState);
            map.put("TextBox1",schoolLoginForm.getUsername());
            map.put("TextBox2",schoolLoginForm.getPassword());
            map.put("RadioButtonList1","学生");
            map.put("Button1","登 录");
            //执行登录操作
            Response quickLogonResponse = okHttpUtils.postLogin(SchoolLoginAddress.TWO_CHECK_LOGIN, map, cookie);
            String quickLogonString = quickLogonResponse.body().string();
            if (quickLogonString.length() != SchoolLoginEnum.TWO_CHECK_LOGIN_ERROR.getCode()) {
                throw new SchoolException(SchoolLoginEnum.TWO_CHECK_LOGIN_ERROR);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //跳转到正常页面
        String loginString = okHttpUtils.get(SchoolLoginAddress.QUICKLOGON + schoolLoginForm.getUsername(), "", cookie);

        //查找数据库是否有该学生
        StudentInfo username = studentInfoService.findByUsername(schoolLoginForm.getUsername());
        if (username == null){

            StudentInfo studentInfo = studentInfo2SchoolLoginForm.convert(schoolLoginForm,HtmlUtils.getName(loginString), (cookie != null ? cookie : ""));

            studentInfoService.save(studentInfo);
            //角色权限生成
            studentUserRoleService.save(StudentInfo2ShiroUseDTO.converter(studentInfo));
            //保存Url数据
            studentPersonService.create(studentInfo,loginString);
        }else{
            username.setStudentCookie(cookie);
            studentInfoService.save(username);
        }
        return loginString;
    }

    @Override
    public void login(SchoolLoginForm schoolLoginForm) {

    }
}
