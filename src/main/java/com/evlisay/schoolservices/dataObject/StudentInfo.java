package com.evlisay.schoolservices.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: EvilSay
 * @Date: 2019/7/9 1:13
 */
@Data
@Entity
@DynamicUpdate
public class StudentInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //名字
    private String name;

    //账号名称
    private String userName;

    //密码
    private String password;

    //密码盐值
    private String salt;

    //账号编号
    private String studentState;

    //校内网Cookie
    private String studentCookie;

    private Integer quickLogon = 0;

}
