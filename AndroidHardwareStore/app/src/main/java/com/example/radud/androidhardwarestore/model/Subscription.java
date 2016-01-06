package com.example.radud.androidhardwarestore.model;

/**
 * Created by radud on 24/12/2015.
 */
public class Subscription {

    private int id;
    private Product product;
    private Member member;

    public Subscription() {
    }

    public Subscription(Product product, Member member) {
        this.product = product;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
