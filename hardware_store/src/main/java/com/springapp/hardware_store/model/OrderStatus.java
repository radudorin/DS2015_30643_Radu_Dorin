package com.springapp.hardware_store.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "order_status", uniqueConstraints = {
        @UniqueConstraint(columnNames = "status_name") })
public class OrderStatus {

    @Id
    @Column(name = "order_status_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "status_name")
    private String statusName;
    @OneToMany
    @JsonIgnore
    @JoinColumn(name="order_status_id")
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
