package com.springapp.hardware_store.controller;

import com.springapp.hardware_store.dao.MemberDAO;
import com.springapp.hardware_store.dao.MemberRoleDAO;
import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.MemberRole;
import com.springapp.hardware_store.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by radud on 03/12/2015.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Member> member() {
        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
        List<Member> members = memberDAO.findAll();

        return members;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Result register(@RequestBody Member member) {
        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
        MemberRoleDAO memberRoleDAO = (MemberRoleDAO) appContext.getBean("memberRoleDao");
        Result result = new Result();

        if (member == null) {
            result.setHasErrors(true);
            result.setMessage("Member is null");
            return result;
        }

        MemberRole memberRole = memberRoleDAO.getByField("roleName", member.getRole().getRoleName());

        member.setRole(memberRole);

        memberDAO.saveOrUpdate(member);
        result.setHasErrors(false);
        return result;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Member deleteMember(@PathVariable("id") int id) {

        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
        Member member = memberDAO.delete(id);

        return member;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Member member(@PathVariable("id") int id) {

        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");

        Member member = memberDAO.findById(id);

        return member;
    }
}
