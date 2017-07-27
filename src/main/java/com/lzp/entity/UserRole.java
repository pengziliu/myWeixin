package com.lzp.entity;

/**
 * Created by liuzp on 2017/7/25.
 * 用户角色
 */
public class UserRole {
    @org.springframework.data.annotation.Id
    private String Id;

    private String userId;

    private String roleId;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
