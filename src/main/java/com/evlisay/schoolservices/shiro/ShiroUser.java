package com.evlisay.schoolservices.shiro;

import com.evlisay.schoolservices.DTO.ShiroUserDTO;
import com.evlisay.schoolservices.Enum.SchoolLoginEnum;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import com.evlisay.schoolservices.dataObject.StudentRole;
import com.evlisay.schoolservices.dataObject.StudentUserRole;
import com.evlisay.schoolservices.service.StudentInfoService;
import com.evlisay.schoolservices.service.StudentRoleService;
import com.evlisay.schoolservices.service.StudentUserRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 20:38
 */
@Service
public class ShiroUser {
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private StudentRoleService studentRoleService;
    @Autowired
    private StudentUserRoleService studentUserRoleService;

    StudentInfo user(String user){
        StudentInfo username = studentInfoService.findByUsername(user);
        if (username == null){
            throw new AuthenticationException(SchoolLoginEnum.LOGIN_PARAM_ERROR.getMessage());
        }
        return username;
    }


    ShiroUserDTO shiroDTO(StudentInfo studentInfo){

        ShiroUserDTO shiroUserDTO = new ShiroUserDTO();

        StudentUserRole studentUserRole = studentUserRoleService.findUserId(studentInfo.getId());

        shiroUserDTO.setUserName(studentInfo.getUserName());

        shiroUserDTO.setStudentState(studentInfo.getStudentState());

        StudentRole studentRole = studentRoleService.findOne(studentUserRole.getRoleId());

        shiroUserDTO.setUserRole(studentRole.getValue());

        return shiroUserDTO;
    }
    //登录密码解密
    SimpleAuthenticationInfo buildAuthenticationInfo(ShiroUserDTO shiroUserDTO, StudentInfo studentInfo, String realmName){
        String credentials = studentInfo.getPassword();

        String salt = studentInfo.getSalt();

        ByteSource credentialsSalt = new Md5Hash(salt);

        return new SimpleAuthenticationInfo(shiroUserDTO,credentials,credentialsSalt,realmName);
    }

}
