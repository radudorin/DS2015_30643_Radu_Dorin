package com.example.radud.androidhardwarestore.model;

import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
public class MemberRole {

    private int id;
    private String roleName;
    List<Member> members;

    public MemberRole() {
    }

    public MemberRole(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
