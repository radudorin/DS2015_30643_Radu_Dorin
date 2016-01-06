package com.springapp.hardware_store.model;

import javax.persistence.*;

/**
 * Created by radud on 24/12/2015.
 */
@Entity
@Table(name = "subscription")

public class Subscription {

    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "member_id")
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
