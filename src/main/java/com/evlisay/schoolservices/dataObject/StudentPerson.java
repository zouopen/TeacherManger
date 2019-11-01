package com.evlisay.schoolservices.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: EvilSay
 * @Date: 2019/7/11 14:05
 */
@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class StudentPerson {

    @Id
    private String perId;
    //个人编号
    private String studentState;
    //成绩
    private String scoreUrl;
    //个人
    private String personUrl;
    //班课
    private String classUrl;
    //密码
    private String passUrl;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
}
