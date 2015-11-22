package com.springapp.hardware_store.model;
import javax.persistence.*;
/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;
    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public ShoppingCart() {
    }

    public ShoppingCart(OrderStatus orderStatus, Member member) {
        this.orderStatus = orderStatus;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
