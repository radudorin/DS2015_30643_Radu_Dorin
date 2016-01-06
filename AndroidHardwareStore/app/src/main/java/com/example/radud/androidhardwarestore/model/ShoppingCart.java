package com.example.radud.androidhardwarestore.model;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
public class ShoppingCart {

    private int id;
    private Member member;
    private List<CartItem> cartItems;
    private double total = 0;

    public ShoppingCart() {
    }

    public ShoppingCart(Member member) {
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
