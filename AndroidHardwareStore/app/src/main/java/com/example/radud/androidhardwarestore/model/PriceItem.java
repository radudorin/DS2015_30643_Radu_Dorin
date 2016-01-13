package com.example.radud.androidhardwarestore.model;

/**
 * Created by radud on 22/11/2015.
 */
public class PriceItem {

    private int id;
    private Product product;
    private double price;
    private String modificationDate;

    public PriceItem() {
    }

    public PriceItem(Product product, double price, String modificationDate) {
        this.product = product;
        this.price = price;
        this.modificationDate = modificationDate;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }
}
