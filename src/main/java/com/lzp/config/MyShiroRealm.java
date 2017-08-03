package com.lzp.config;

import com.lzp.dao.UserRepository;
import com.lzp.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * http://www.jianshu.com/p/672abf94a857
 * Created by liuzp on 2017/7/27.

 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。因为在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的。通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO.

 Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法。
 该方法主要执行以下操作:

 1、检查提交的进行认证的令牌信息

 2、根据令牌信息从数据源(通常为数据库)中获取用户信息

 3、对用户信息进行匹配验证。

 4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。

 5、验证失败则抛出AuthenticationException异常信息。

 而在我们的应用程序中要做的就是自定义一个Realm类，继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo
 ()，重写获取用户信息的方法。



 *
 */
//@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *  授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        User token = (User) SecurityUtils.getSubject().getPrincipal();
        String userId = token.getId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id", userId);
        List<SysRole> roleList = sysRoleService.selectByMap(map);
        Set<String> roleSet = new HashSet<String>();
        for(SysRole role : roleList){
            roleSet.add(role.getType());
        }*/
        //实际开发，当前登录用户的角色和权限信息是从数据库来获取的，我这里写死是为了方便测试
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);
        //根据用户ID查询权限（permission），放入到Authorization里。
        /*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
        Set<String> permissionSet = new HashSet<String>();
        for(SysPermission Permission : permissionList){
            permissionSet.add(Permission.getName());
        }*/
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        String userName = (String) authenticationToken.getPrincipal();
        String passwd = (String) authenticationToken.getCredentials();

        // 从数据库获取对应用户名密码的用户(此处自由扩展)
        User user = userRepository.findByUserName(userName);
        if(user==null || !passwd.equals(user.getPasswd())){
            throw new AccountException("帐号或密码不正确！");
        }
        return new SimpleAuthenticationInfo(user, user.getPasswd(), getName());
    }
}
