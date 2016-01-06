package com.example.radud.androidhardwarestore.model;

/**
 * Created by radud on 05/12/2015.
 */
public class CartItem {

    private int id;
    private ShoppingCart shoppingCart;
    private Product product;
    private double quantity;


    public CartItem() {
    }

    public CartItem(ShoppingCart shoppingCart, Product product, double quantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
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
