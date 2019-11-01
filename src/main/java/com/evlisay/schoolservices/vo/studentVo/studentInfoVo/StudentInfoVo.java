package com.evlisay.schoolservices.vo.studentVo.studentInfoVo;

import com.evlisay.schoolservices.vo.studentVo.studentInfoVo.StudentInfoPersonVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 19:43
 */
@Data
public class StudentInfoVo {
    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("numbers")
    private String userName;

    @JsonProperty("info")
    private List<StudentInfoPersonVo> person;
}