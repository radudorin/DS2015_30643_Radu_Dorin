package com.springapp.hardware_store.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @Column(name = "shopping_cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "shopping_cart_id")
    private List<CartItem> cartItems;
    @Column(name = "total")
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
