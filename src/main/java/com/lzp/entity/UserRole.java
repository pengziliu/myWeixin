package com.lzp.entity;

/**
 * Created by liuzp on 2017/7/25.
 * 用户角色
 */
public class UserRole {
    @org.springframework.data.annotation.Id
    private String Id;

    private String userName;

    private String roleName;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
