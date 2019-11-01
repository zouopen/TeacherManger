package com.evlisay.schoolservices.vo.studentVo.studentInfoVo;

import lombok.Data;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 22:33
 */
@Data
public class StudentInfoPersonVo {

    private String studentNumber; //学号

    private String studentName; // 学生姓名

    private String studentSex; // 性别

    private String studentStart; // 出生日期

    private String studentNation; // 民族

    private String studentPc; // 政治面貌

    private String studentFromPlace; // 来源地区

    private String studentFrom; //来源省

    private String studentCollege; //学院

    private String studentPN;// 系办

    private String studentClass; // 专业名称

    private String intakeTime; // 入学日期

    private String studentGraduate; // 毕业学校

    private String studentInfoNumber; // 身份证
}
