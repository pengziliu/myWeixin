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

    private String desc;//miaoshu

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
