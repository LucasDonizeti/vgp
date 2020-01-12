package com.vgproject.vgp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;


/**
 * author LucasDonizeti
 */
@Entity
public class User extends AbstractEntity {
    private String name;
    private String username;
    //@JsonIgnore
    private String password;
    private boolean admin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
