package com.example.demo.shiro.realm;

import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysToken;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.service.ISysMenuService;
import com.example.demo.core.service.ISysRoleService;
import com.example.demo.core.service.ISysTokenService;
import com.example.demo.core.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    ISysUserService userService;

    @Autowired
    ISysRoleService roleService;

    @Autowired
    ISysMenuService menuService;

    @Autowired
    ISysTokenService tokenService;

    /**
     * 权限配置类
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<SysRole> roles = roleService.selectRolesByUserId(user.getUserId());
        List<SysMenu> menus = menuService.selectMenusByUserId(user.getUserId());

        System.out.println(menus);

        for (SysRole role : roles) {
            String code = role.getCode();
            if(StringUtils.hasText(code)){
                simpleAuthorizationInfo.addRole(code);
            }
        }
        for (SysMenu menu : menus) {
            String code = menu.getCode();
            if(StringUtils.hasText(code)) {
                simpleAuthorizationInfo.addStringPermission(code);
            }
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) authenticationToken.getPrincipal();

        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = tokenService.selectTokenByToken(accessToken);

        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException();
        }

        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        SysUser user = userService.selectUserById(tokenEntity.getUserId());

        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException();
        }

        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}
