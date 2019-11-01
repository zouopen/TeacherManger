package com.evlisay.schoolservices.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 23:06
 */
@Data
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 324933513224799226L;

    private Integer code;

    private String message;

    private T data;
}
