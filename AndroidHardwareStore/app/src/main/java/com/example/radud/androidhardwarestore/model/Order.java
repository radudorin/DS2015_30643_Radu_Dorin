package com.example.radud.androidhardwarestore.model;

import java.util.Date;
import java.util.List;

/**
 * Created by radud on 04/12/2015.
 */
public class Order {

    private int id;
    private Date deliveryDate;
    private Date orderDate;
    private double total;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(List<OrderItem> orderItems, Date deliveryDate, Date orderDate, double total, OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
