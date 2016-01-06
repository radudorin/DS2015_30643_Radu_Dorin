package com.example.radud.androidhardwarestore.model;

/**
 * Created by radud on 22/11/2015.
 */
public class Rating {

    private int id;
    private Member member;
    private Product product;
    private int value;
    String comment;

    public Rating() {
    }

    public Rating(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
