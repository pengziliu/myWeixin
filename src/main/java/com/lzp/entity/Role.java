package com.lzp.entity;

import org.springframework.data.annotation.Id;

/**
 * 角色
 */
public class Role {

    @Id
    private String id;

    private String roleName;

    private String permission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
