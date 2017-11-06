package com.java.kv_30.kvapp.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by TanyaOhotnik on 06.11.2017.
 */

public class Resource {
    private Long id;
    private double size;
    private Date expirationTime;
    private String name;
    private Permission permission;
    private byte[] file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
