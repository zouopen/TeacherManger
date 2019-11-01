package com.evlisay.schoolservices.utils;

import com.evlisay.schoolservices.vo.studentVo.classInfoV0.StudentClassInfo2Vo;
import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoPersonVo;
import com.evlisay.schoolservices.vo.studentVo.studentVo.StudentScoreInfoVo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 22:59
 * WARNING 谁都不要尝试维护这些代码！！
 */
public class InfoUtils {
    //个人信息
    public static StudentInfoPersonVo infoPersonVo(String htmlData){

        Document document = Jsoup.parse(htmlData);

        StudentInfoPersonVo studentInfoPersonVo  = new StudentInfoPersonVo();

        studentInfoPersonVo.setIntakeTime(document.getElementById("lbl_rxrq").select("span").text()); // 入学日期

        studentInfoPersonVo.setStudentClass(document.getElementById("lbl_zymc").select("span").text()); // 专业名称

        studentInfoPersonVo.setStudentCollege(document.getElementById("lbl_xy").select("span").text()); //学院

        studentInfoPersonVo.setStudentFrom(document.getElementById("lbl_lys").select("span").text()); //来源省

        studentInfoPersonVo.setStudentFromPlace(document.getElementById("lbl_lydq").select("span").text()); // 来源地区

        studentInfoPersonVo.setStudentGraduate(document.getElementById("lbl_byzx").select("span").text()); // 毕业学校

        studentInfoPersonVo.setStudentInfoNumber(document.getElementById("lbl_sfzh").select("span").text()); // 身份证

        studentInfoPersonVo.setStudentName(document.getElementById("xm").select("span").text()); //学生姓名

        studentInfoPersonVo.setStudentNation(document.getElementById("lbl_mz").select("span").text()); // 名称

        studentInfoPersonVo.setStudentPc(document.getElementById("lbl_zzmm").select("span").text()); // 政治面貌

        studentInfoPersonVo.setStudentPN(document.getElementById("lbl_xi").select("span").text()); //系办

        studentInfoPersonVo.setStudentSex(document.getElementById("lbl_xb").select("span").text()); //性别

        studentInfoPersonVo.setStudentStart(document.getElementById("lbl_csrq").select("span").text()); // 出生日期

        studentInfoPersonVo.setStudentNumber(document.getElementById("xh").select("span").text()); //学号

        return studentInfoPersonVo;
    }
    //成绩
    public static List<StudentScoreInfoVo> infoScoreVo(String htmlData, String classTerm2) {
        List<StudentScoreInfoVo> studentScoreInfoVos = new ArrayList<>();
        int i = 3;
        Document document = Jsoup.parse(htmlData);
        while (true) try {
            StudentScoreInfoVo studentScoreInfoVo = new StudentScoreInfoVo();
            i++;
            String classTerm = document.select("table[id=Datagrid1] > tbody")
                    .select("tr").get(i).select("td").get(1).text();
            String className = document.select("table[id=Datagrid1] > tbody")
                    .select("tr").get(i).select("td").get(3).text();
            String classInfo = document.select("table[id=Datagrid1] > tbody")
                    .select("tr").get(i).select("td").get(4).text();
            String credit = document.select("table[id=Datagrid1] > tbody")
                    .select("tr").get(i).select("td").get(6).text();
            String classScore = document.select("table[id=Datagrid1] > tbody")
                    .select("tr").get(i).select("td").get(8).text();
            String classFrom = document.select("table[id=Datagrid1] > tbody")
                    .select("tr").get(i).select("td").get(12).text();
            if (classTerm.equals(classTerm2)){
                studentScoreInfoVo.setClassTerm(classTerm);
                studentScoreInfoVo.setClassName(className);
                studentScoreInfoVo.setClassInfo(classInfo);
                studentScoreInfoVo.setClassCredit(credit);
                studentScoreInfoVo.setClassScore(classScore);
                studentScoreInfoVo.setClassFrom(classFrom);
                studentScoreInfoVos.add(studentScoreInfoVo);
            }else if (classTerm2.equals("")){
                studentScoreInfoVo.setClassTerm(classTerm);
                studentScoreInfoVo.setClassName(className);
                studentScoreInfoVo.setClassInfo(classInfo);
                studentScoreInfoVo.setClassCredit(credit);
                studentScoreInfoVo.setClassScore(classScore);
                studentScoreInfoVo.setClassFrom(classFrom);
                studentScoreInfoVos.add(studentScoreInfoVo);
            }
        } catch (IndexOutOfBoundsException e) {
            break;
        }
        return studentScoreInfoVos;
    }
    //课表
    public static List<StudentClassInfo2Vo> infoClass(String htmlData){
        Document document = Jsoup.parse(htmlData);
        List<StudentClassInfo2Vo> studentClassInfo2Vos = new ArrayList<>();
        Elements select = document.select("table[class=blacktab] > tbody");
        int day   = 0; //循环天数
        int index = 1;
        int author = 1;
        while (true) {
            StudentClassInfo2Vo studentClassInfo2Vo = new StudentClassInfo2Vo();
            for (Element element : select) {
                for (int i = 2; i <= 12; i += 2) {
                    if (i == 12) {
                        day += 1;
                        studentClassInfo2Vo.setDate("星期"+String.valueOf(day));
                        //循环体管理课程为(3 4)( 7 8)(11)
                        for (int j = 4; j <= 11; j += 4) { // 1 2
                            String mondayClassMorningOne = element.select("tr").get(j).select("td").get(index).text();
                            if (j == 4) {
                                studentClassInfo2Vo.setThreeFourCourse(mondayClassMorningOne);
                            } else if (j == 8) {
                                studentClassInfo2Vo.setSevenNightCourse(mondayClassMorningOne);
                            }

                        }
                        //循环体管理课程为(1 2) (5 6) (9 10)
                        author+= 1;
                        for (int j = 2; j <= 10; j += 4) {// 2 1
                            String mondayClassMorningOne = element.select("tr").get(j).select("td").get(author).text();
                            if (j == 2){
                                studentClassInfo2Vo.setOneTwoCourse(mondayClassMorningOne);
                            }else if (j ==6){
                               studentClassInfo2Vo.setFiveSixCourse(mondayClassMorningOne);
                            }else if (j == 10){
                                studentClassInfo2Vo.setNineTenCourse(mondayClassMorningOne);
                            }
                        }
                        studentClassInfo2Vos.add(studentClassInfo2Vo);
                    }
                }
                index++;
            }
            return studentClassInfo2Vos;
        }
    }
}

