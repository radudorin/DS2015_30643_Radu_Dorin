package com.springapp.hardware_store.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "member_role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "role_name")})
public class MemberRole {

    @Id
    @Column(name = "member_role")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "role_name")
    private String roleName;
    @OneToMany
    @JoinColumn(name = "role_id")
    List<Member> members;

    public MemberRole() {
    }

    public MemberRole(String roleName) {
        this.roleName = roleName;
    }

    public List<Member> getMemberList() {
        return members;
    }

    public void setMemberList(List<Member> memberList) {
        this.members = memberList;
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
