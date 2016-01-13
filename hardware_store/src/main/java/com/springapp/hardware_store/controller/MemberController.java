package com.springapp.hardware_store.controller;

import com.springapp.hardware_store.dao.*;
import com.springapp.hardware_store.model.*;
import com.springapp.hardware_store.utils.MailUtils;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Result register(@RequestParam(value = "password") String password, @RequestParam(value = "username") String username) {
        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
        Member member = memberDAO.getByField("username", username);
        Result<Member> result = new Result<Member>();
        if (member == null) {
            result.setHasErrors(true);
            result.setMessage("Invalid username");
            return result;
        }


        if (!member.getPassword().equals(password)) {
            result.setHasErrors(true);
            result.setMessage("Wrong password");
            return result;
        }

        result.setResponse(member);

        return result;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Result register(@RequestBody Member member) {
        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
        MemberRoleDAO memberRoleDAO = (MemberRoleDAO) appContext.getBean("memberRoleDao");
        ShoppingCartDAO shoppingCartDAO = (ShoppingCartDAO) appContext.getBean("shoppingCartDao");
        Result<Member> result = new Result<Member>();

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
        result.setResponse(memberDAO.findById(id));

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
    Result<ShoppingCart> getShopingCart(@PathVariable("id") int id) {
        ShoppingCartDAO shoppingCartDAO = (ShoppingCartDAO) appContext.getBean("shoppingCartDao");
        ShoppingCart shoppingCart = shoppingCartDAO.getShoppingCartForMember(id);

        Result<ShoppingCart> result = new Result<ShoppingCart>();

        if (shoppingCart == null) {
            result.setHasErrors(true);
            result.setMessage("Shopping cart is null");
            return result;
        }

        result.setResponse(shoppingCart);

        return result;
    }

    @RequestMapping(value = "/shoppingCart/add/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Result<ShoppingCart> addCartItem(@PathVariable("id") int id, @RequestBody CartItem cartItem) {
        ShoppingCartDAO shoppingCartDAO = (ShoppingCartDAO) appContext.getBean("shoppingCartDao");
        ShoppingCart shoppingCart = shoppingCartDAO.getShoppingCartForMember(id);

        Result<ShoppingCart> result = new Result<ShoppingCart>();

        if (shoppingCart == null) {
            result.setHasErrors(true);
            result.setMessage("Shopping cart is null");
            return result;
        }

        shoppingCart.getCartItems().add(cartItem);
        shoppingCartDAO.save(shoppingCart);

        result.setResponse(shoppingCart);

        return result;
    }

    @RequestMapping(value = "/orders/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Result<List<Order>> getOrders(@PathVariable("id") int id) {
        OrderDAO orderDao = (OrderDAO) appContext.getBean("orderDao");
        List<Order> orders = orderDao.getOrdersForMember(id);

        Result<List<Order>> result = new Result<List<Order>>();

        if (orders == null || orders.isEmpty()) {
            result.setHasErrors(true);
            result.setMessage("No orders found.");
            return result;
        }

        result.setResponse(orders);

        return result;
    }
}
