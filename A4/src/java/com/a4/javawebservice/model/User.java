/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a4.javawebservice.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author radud
 */
@XmlRootElement
public class User implements Serializable {
    
    private int id;
    
    private String username;
    
    private String password;
    
    private String fullName;
    
    private boolean admin;

    public User() {
    }

    public User(int id, String username, String password, String fullName, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.admin = admin;
    }
    
    public User(String username, String password, String fullName, boolean admin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
    
}
