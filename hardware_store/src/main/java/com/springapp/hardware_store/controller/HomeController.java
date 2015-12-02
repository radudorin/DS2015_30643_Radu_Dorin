package com.springapp.hardware_store.controller;

import com.springapp.hardware_store.dao.GenericDAO;
import com.springapp.hardware_store.dao.MemberDAO;
import com.springapp.hardware_store.dao.MemberDAOImpl;
import com.springapp.hardware_store.model.Member;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by radud on 22/11/2015.
 */
@Controller
public class HomeController {

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public
    @ResponseBody
    Member member(@RequestParam(value = "id") int id) {

//        GenericDAO categoryDao = (GenericDAO) appContext.getBean("categoryDao");
//        System.out.println(((ProductCategory) categoryDao.read(1)).getName());
        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");

        Member member = memberDAO.findById(id);

        return member;
    }
//
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }


//    @RequestMapping(value = "Add", method = RequestMethod.GET)
//    public ProductCategory getCategory() {
//
////        GenericDAO dao = (GenericDAO) getBean("personDao");
////        categoryDao.read(1);
//
//        return (ProductCategory) categoryDao.read(1);
//    }

}
