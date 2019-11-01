package com.evlisay.schoolservices.shiro;

import com.evlisay.schoolservices.DTO.ShiroUserDTO;
import com.evlisay.schoolservices.dataObject.StudentInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 18:37
 */
public class ShiroReaml extends AuthorizingRealm {

    @Autowired
    private ShiroUser shiroUser;
    /**
     * 获取身份信息，从数据库获取该用户的权限和角色信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRole(shiroUserDTO.getUserRole());

        info.addStringPermission("/index");

        return info;
    }

    /**
     * 密码验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        StudentInfo studentInfo = shiroUser.user(usernamePasswordToken.getUsername());

        ShiroUserDTO shiroUserDTO = shiroUser.shiroDTO(studentInfo);

        return shiroUser.buildAuthenticationInfo(shiroUserDTO,studentInfo,super.getName());
    }
    //密码匹配
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        //加密次数
        hashedCredentialsMatcher.setHashIterations(ShiroKit.hashIterations);

        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
