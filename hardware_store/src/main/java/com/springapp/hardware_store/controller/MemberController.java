package com.springapp.hardware_store.controller;

import com.springapp.hardware_store.dao.*;
import com.springapp.hardware_store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        ShoppingCartDAO shoppingCartDAO = (ShoppingCartDAO) appContext.getBean("shoppingCartDao");
        Result result = new Result();

        if (member == null) {
            result.setHasErrors(true);
            result.setMessage("Member is null");
            return result;
        }

        MemberRole memberRole = memberRoleDAO.getByField("roleName", "user");

        member.setRole(memberRole);

        int id = memberDAO.save(member);

        shoppingCartDAO.save(new ShoppingCart(memberDAO.findById(id)));

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

    @RequestMapping(value = "/rating/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Rating> getRatingsForMember(@PathVariable("id") int id) {
        RatingDAO ratingDAO = (RatingDAO) appContext.getBean("ratingDao");
        List<Rating> ratings = ratingDAO.findRatingsForMember(id);

        return ratings;
    }

    @RequestMapping(value = "/shoppingCart/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ShoppingCart getShopingCart(@PathVariable("id") int id) {
        ShoppingCartDAO shoppingCartDAO = (ShoppingCartDAO) appContext.getBean("shoppingCartDao");
        ShoppingCart shoppingCart = shoppingCartDAO.getShoppingCartForMember(id);

        return shoppingCart;
    }

    @RequestMapping(value = "/orders/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getOrders(@PathVariable("id") int id) {
        OrderDAO orderDao = (OrderDAO) appContext.getBean("orderDao");
        List<Order> orders = orderDao.getOrdersForMember(id);

        return orders;
    }

    @RequestMapping(value = "/orders/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Result placeOrder(@PathVariable("id") int id, @RequestBody Date deliveryDate) {
        ShoppingCartDAO shoppingCartDAO = (ShoppingCartDAO) appContext.getBean("shoppingCartDao");
        ShoppingCart shoppingCart = shoppingCartDAO.getShoppingCartForMember(id);

        shoppingCart.getCartItems();



        Result result = new Result();
        result.setHasErrors(false);
        result.setMessage("Succes");

        return result;
    }
}
