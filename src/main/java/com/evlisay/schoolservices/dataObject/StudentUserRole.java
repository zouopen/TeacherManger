package com.evlisay.schoolservices.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 20:54
 */
@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class StudentUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer roleId;
    @CreatedDate
    private Date createTime;
}
