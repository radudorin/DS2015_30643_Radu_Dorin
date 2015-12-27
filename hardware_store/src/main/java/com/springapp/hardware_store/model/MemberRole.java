package com.springapp.hardware_store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "member_role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "role_name")})
public class MemberRole extends BaseResponse {

    @Id
    @Column(name = "member_role")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "role_name")
    private String roleName;
    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "role_id")
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
