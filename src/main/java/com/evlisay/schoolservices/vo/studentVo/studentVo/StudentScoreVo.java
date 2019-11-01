package com.evlisay.schoolservices.vo.studentVo.studentVo;

import com.evlisay.schoolservices.vo.studentVo.studentVo.StudentScoreInfoVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/7/13 14:06
 */
@Data
public class StudentScoreVo {

    private String name;

    @JsonProperty("score")
    private List<StudentScoreInfoVo> studentScoreInfoVoList;
}
