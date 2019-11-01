package com.evlisay.schoolservices.vo.studentVo.studentVo;

import lombok.Data;

/**
 * @Author: EvilSay
 * @Date: 2019/7/13 14:06
 */
@Data
public class StudentScoreInfoVo {

    //学期
    private String classTerm;

    //课程名称
    private String className;

    //课程性质
    private String classInfo;

    //学分
    private String classCredit;

    //课程成绩
    private String classScore;

    //学院
    private String classFrom;
}
