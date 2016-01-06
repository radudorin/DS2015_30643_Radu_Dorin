package com.example.radud.androidhardwarestore.model;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
public class Product {

    private int id;
    private String name;
    private ProductCategory category;
    private String description;
    private int stock;
    private double price;
    private String imageSource;
    List<PriceItem> priceItems;

    public Product() {
    }

    public Product(String name, String description, int stock, double price, String imageSource) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.imageSource = imageSource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public List<PriceItem> getPriceItems() {
        return priceItems;
    }

    public void setPriceItems(List<PriceItem> priceItems) {
        this.priceItems = priceItems;
    }
}
