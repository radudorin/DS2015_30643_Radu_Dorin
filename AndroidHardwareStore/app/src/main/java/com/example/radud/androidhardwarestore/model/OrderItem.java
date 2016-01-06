package com.example.radud.androidhardwarestore.model;

/**
 * Created by radud on 22/11/2015.
 */
public class OrderItem {

    private int id;
    private Order order;
    private Product product;
    private double quantity;


    public OrderItem() {
    }

    public OrderItem(Order order, Product product, double quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
