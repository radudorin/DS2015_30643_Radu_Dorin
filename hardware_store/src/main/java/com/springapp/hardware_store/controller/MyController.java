package com.springapp.hardware_store.controller;

import com.springapp.hardware_store.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by radud on 05/12/2015.
 */
@Controller
@RequestMapping("/populate")
public class MyController {

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public
    @ResponseBody
    Result member() {
//
//        MemberRoleDAO memberRoleDAO = (MemberRoleDAO) appContext.getBean("memberRoleDao");
//        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
//        ProductCategoryDAO productCategoryDAO = (ProductCategoryDAO) appContext.getBean("productCategoryDao");
//        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
//        RatingDAO ratingDAO = (RatingDAO) appContext.getBean("ratingDao");
//
//        MemberRole memberRole1 = new MemberRole("admin");
//        MemberRole memberRole2 = new MemberRole("user");
//
//        memberRoleDAO.saveOrUpdate(memberRole1);
//        memberRoleDAO.saveOrUpdate(memberRole2);
//
//        memberRole1
//
//
//        Member member1 = new Member("radudorin", "123123", "radudorin@gmail.com", "Radu Dorin", "Cluj-Napoca", "079797979", "12.06.1993", 1);
//        Member member2 = new Member("radudorin", "123123", "radudorin@gmail.com", "Radu Dorin", "Cluj-Napoca", "079797979", "12.06.1993", 2);
//
//
//
//
        return result;
    }

}
