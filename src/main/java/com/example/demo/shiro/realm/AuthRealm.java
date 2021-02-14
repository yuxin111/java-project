package com.example.demo.shiro.realm;

import com.example.demo.shiro.entity.SysMenu;
import com.example.demo.shiro.entity.SysRole;
import com.example.demo.shiro.entity.SysToken;
import com.example.demo.shiro.entity.SysUser;
import com.example.demo.shiro.service.IShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    IShiroService shiroService;

    /**
     * 权限配置类
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        //2. 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (SysRole role : user.getRoles()) {
            //2.1 添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (SysMenu menu : role.getMenus()) {
                //2.1.1 添加菜单
                simpleAuthorizationInfo.addStringPermission(menu.getMenuName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(authenticationToken.getCredentials());
        //获取token，既前端传入的token
        String accessToken = (String) authenticationToken.getPrincipal();

        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.selectTokenByToken(accessToken);

        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        SysUser user = shiroService.selectUserByUserId(tokenEntity.getUserId());

        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }

        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}