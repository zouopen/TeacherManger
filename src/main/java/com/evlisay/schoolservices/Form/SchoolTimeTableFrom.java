package com.evlisay.schoolservices.Form;

import lombok.Data;

/**
 * @Author: EvilSay
 * @Date: 2019/7/15 0:13
 */
@Data
public class SchoolTimeTableFrom {

    private String name = "";
    //系部
    private String sdept = "";
    //学年
    private String year = "";
    //学期
    private String term = "";
    //年级
    private String grade = "";
    //专业
    private String major = "";
    //所属班讲课表
    private String timetable= "";
}
