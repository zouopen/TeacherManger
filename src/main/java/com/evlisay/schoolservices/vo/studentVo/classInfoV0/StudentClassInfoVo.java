package com.evlisay.schoolservices.vo.studentVo.classInfoV0;
import lombok.Data;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/7/13 14:06
 */
@Data
public class StudentClassInfoVo {
    
    private String name;

    private List<StudentClassInfo2Vo> day;
}
