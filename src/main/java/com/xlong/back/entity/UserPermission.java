package com.xlong.back.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/17.
 */
@Entity
@Table(name="user_permission")
public class UserPermission {
    @Id
    @GeneratedValue
    private int id;
    private String permission;
    @Column(name="user_id")
    private int userId;

    public UserPermission() {
    }

    public UserPermission(String permission, int userId) {
        this.permission = permission;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
