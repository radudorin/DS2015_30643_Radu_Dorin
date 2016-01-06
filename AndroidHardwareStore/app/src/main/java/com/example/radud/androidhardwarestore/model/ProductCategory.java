package com.example.radud.androidhardwarestore.model;

import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
public class ProductCategory {

    private int id;
    private String name;
    List<Product> products;
    private String imageUrl;

    public ProductCategory() {
    }

    public ProductCategory(String name, String imageUrl) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
