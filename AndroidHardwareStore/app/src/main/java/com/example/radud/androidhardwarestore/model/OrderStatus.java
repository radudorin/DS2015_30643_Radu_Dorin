package com.example.radud.androidhardwarestore.model;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
public class OrderStatus {

    private int id;
    private String statusName;
    private List<Order> orders;

    public OrderStatus() {
    }

    public OrderStatus(String statusName, List<Order> orders) {
        this.statusName = statusName;
        this.orders = orders;
    }

    public OrderStatus(String statusName) {
        this.statusName = statusName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
