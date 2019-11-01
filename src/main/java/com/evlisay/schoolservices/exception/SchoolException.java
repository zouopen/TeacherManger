package com.evlisay.schoolservices.exception;

import com.evlisay.schoolservices.Enum.SchoolLoginEnum;
import com.evlisay.schoolservices.Enum.SchoolParameterEnum;
import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 23:25
 */
@Getter
public class SchoolException extends RuntimeException {
    private Integer code;

    public SchoolException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public SchoolException(SchoolLoginEnum schoolLoginEnum) {
        super(schoolLoginEnum.getMessage());
        this.code = schoolLoginEnum.getCode();
    }

    public SchoolException(SchoolParameterEnum schoolParameterEnum) {
        super(schoolParameterEnum.getMessage());
        this.code = schoolParameterEnum.getCode();
    }
}
