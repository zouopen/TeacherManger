package com.evlisay.schoolservices.controller;

import com.evlisay.schoolservices.Enum.SchoolLoginEnum;
import com.evlisay.schoolservices.constant.SchoolLoginAddress;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.exception.SchoolException;
import com.evlisay.schoolservices.service.StudentInfoService;
import com.evlisay.schoolservices.service.StudentPersonService;
import com.evlisay.schoolservices.shiro.ShiroKit;
import com.evlisay.schoolservices.utils.InfoUtils;
import com.evlisay.schoolservices.utils.OkHttpUtils;
import com.evlisay.schoolservices.utils.ResultVOUtils;
import com.evlisay.schoolservices.vo.ResultVo;
import com.evlisay.schoolservices.vo.studentVo.classInfoV0.StudentClassInfoVo;
import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoPersonVo;
import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoVo;
import com.evlisay.schoolservices.vo.studentVo.studentVo.StudentScoreInfoVo;
import com.evlisay.schoolservices.vo.studentVo.studentVo.StudentScoreVo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 19:35
 */
@RestController
@Slf4j
@RequestMapping("/person")
public class SchoolStudentController {

    @Autowired
    private StudentInfoService studentInfoService;
    @Resource
    private OkHttpUtils okHttpUtils;
    @Autowired
    private StudentPersonService studentPersonService;
    @GetMapping("/info")
    public ResultVo studentInfo(){

        StudentInfo studentInfo = studentInfoService.findByUsername(ShiroKit.getUserDTO().getUserName());
        List<StudentInfoPersonVo> infoList = new ArrayList<>();
        StudentInfoVo studentInfoVo = new StudentInfoVo();
        //主体中获取StudentState
        String url = SchoolLoginAddress.BASE_URL + studentPersonService.returnUrl(SchoolLoginAddress.PERSON_URL, studentInfo.getStudentState());
        //返回的需要处理的数据
        String person = okHttpUtils.get(url, SchoolLoginAddress.HEADER + studentInfo.getUserName(),studentInfo.getStudentCookie());
        //检查服务器Cookie是否失效！
        checkData(person);

        BeanUtils.copyProperties(studentInfo,studentInfoVo);

        infoList.add(InfoUtils.infoPersonVo(person));

        studentInfoVo.setPerson(infoList);

        return ResultVOUtils.success(studentInfoVo);
    }
    @GetMapping("/score")
    public ResultVo studentScore(@RequestParam(value = "classTrem",defaultValue = "")String classTrem){
        StudentInfo studentInfo = studentInfoService.findByUsername(ShiroKit.getUserDTO().getUserName());
        //成绩网址
        String url = SchoolLoginAddress.BASE_URL + studentPersonService.returnUrl(SchoolLoginAddress.SCORE_URL, studentInfo.getStudentState());

        String referer = SchoolLoginAddress.HEADER + studentInfo.getUserName();

        String response = okHttpUtils.get(url, referer, studentInfo.getStudentCookie());
        //获取教务网state
        String viewState = Jsoup.parse(response).select("input[name=__VIEWSTATE]").attr("value");

        String scoreData = scoreData(url, viewState, referer, studentInfo.getStudentCookie());

        //检查服务器Cookie是否失效！
        checkData(scoreData);

        StudentScoreVo studentScoreVo = new StudentScoreVo();

        studentScoreVo.setName(studentInfo.getName());

        List<StudentScoreInfoVo> scoreVo = InfoUtils.infoScoreVo(scoreData,classTrem);

        studentScoreVo.setStudentScoreInfoVoList(scoreVo);

        return ResultVOUtils.success(studentScoreVo);
    }
    @GetMapping("/courseInfo")
    public ResultVo scoreInfo(){
        //主体中获取StudentState
        StudentInfo studentInfo = studentInfoService.findByUsername(ShiroKit.getUserDTO().getUserName());

        String url = SchoolLoginAddress.BASE_URL + studentPersonService.returnUrl(SchoolLoginAddress.CLASS_INFO_URL, studentInfo.getStudentState());

        String referer = SchoolLoginAddress.HEADER + studentInfo.getUserName();

        String response = okHttpUtils.get(url, referer, studentInfo.getStudentCookie());

        checkData(response);

        StudentClassInfoVo studentClassInfoVo = new StudentClassInfoVo();

        studentClassInfoVo.setName(studentInfo.getName());

        studentClassInfoVo.setDay(InfoUtils.infoClass(response));

        return ResultVOUtils.success(studentClassInfoVo);

    }
    @PostMapping("/repassword")
    public ResultVo rePassword(@RequestParam("password")String password, HttpServletRequest httpServletRequest) throws IOException {

        if (password == null){
            throw new SchoolException(SchoolLoginEnum.LOGIN_PARAM_ERROR);
        }

        StudentInfo studentInfo = studentInfoService.findByUsername(ShiroKit.getUserDTO().getUserName());

        Response response = okHttpUtils.getCookie(SchoolLoginAddress.TWO_CHECK_LOGIN);

        String set_Cookie = response.networkResponse().header("Set-Cookie");

        StringTokenizer stringTokenizer = new StringTokenizer(set_Cookie, ";");
        //获取快速登录的Cookie
        String cookie = stringTokenizer.nextToken();

        String string = response.body().string();
        //可能每个人都不同的ViewState
        String viewState = Jsoup.parse(string).select("input[name=__VIEWSTATE]").attr("value");
        //返回快速登录的Cookie通知数据库更新
        String s = twoCheckPassword(studentInfo.getUserName(), password, viewState,cookie,studentInfo);

        HttpSession httpSession = httpServletRequest.getSession();

        httpSession.setAttribute("checkCookie",s);

        studentInfo.setStudentCookie(s);

        studentInfoService.save(studentInfo);

        return ResultVOUtils.LoginSuccess();
    }


    //成绩表
    private String scoreData(String url, String viewState, String referer, String cookie){
        Map<String,String> map = new HashMap<>();
        map.put("__EVENTTARGET","ddl_kcxz");
        map.put("__EVENTARGUMENT","");
        map.put("__VIEWSTATE",viewState);
        map.put("ddlXN","");
        map.put("ddlXQ","");
        map.put("ddl_kcxz","");
        map.put("btn_zcj","历年成绩");
        return okHttpUtils.post(url, map, referer, cookie);
    }

    //二次登录免验证码
    private String twoCheckPassword(String account, String password, String VEGETATES, String cookie,StudentInfo studentInfo){

        Map<String,String> map = new HashMap<>();
        map.put("__VIEWSTATE",VEGETATES);
        map.put("TextBox1",account);
        map.put("TextBox2",password);
        map.put("RadioButtonList1","学生");
        map.put("Button1","登 录");

        try (Response response = okHttpUtils.postLogin(SchoolLoginAddress.TWO_CHECK_LOGIN, map,cookie)) {
            String returnCookie = response.networkResponse().request().header("Cookie");
            String string = response.body().string();
            if (string.length() != SchoolLoginEnum.TWO_CHECK_LOGIN_ERROR.getCode()) {
                studentInfo.setQuickLogon(studentInfo.getQuickLogon() + 1);
                studentInfoService.save(studentInfo);
                throw new SchoolException(SchoolLoginEnum.TWO_CHECK_LOGIN_ERROR);
            }
            return returnCookie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //检查Cookies是否失效
    private void checkData(String data){
        if (data.length() < SchoolLoginEnum.GET_INFO_DATA_ERROR.getCode()
                || data.length() == SchoolLoginEnum.GET_INFO_NO_DATA_ERROR.getCode()){
            throw new SchoolException(SchoolLoginEnum.GET_INFO_DATA_ERROR);
        }
    }
}
