package com.lzp.entity;

import org.springframework.data.annotation.Id;

/**
 * 用户
 */
public class User {

    @Id
    private String id;

    private  String userName;

    private String passwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
