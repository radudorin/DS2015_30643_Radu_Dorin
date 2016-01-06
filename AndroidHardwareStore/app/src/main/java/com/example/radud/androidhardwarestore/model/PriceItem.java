package com.example.radud.androidhardwarestore.model;
import java.sql.Timestamp;

/**
 * Created by radud on 22/11/2015.
 */
public class PriceItem {

    private int id;
    private Product product;
    private double price;
    private Timestamp modificationDate;

    public PriceItem() {
    }

    public PriceItem(Product product, double price, Timestamp modificationDate) {
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

    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }
}
