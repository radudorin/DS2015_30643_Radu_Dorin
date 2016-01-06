package com.springapp.hardware_store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "product_category", uniqueConstraints = {
        @UniqueConstraint(columnNames = "category_name")})
public class ProductCategory {

    @Id
    @Column(name = "product_category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "category_name")
    private String name;
    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "product_category_id")
    List<Product> products;
    @Column(name = "image_url")
    private String imageUrl;

    public ProductCategory() {
    }

    public ProductCategory(String name, String imageUrl) {
        this.name = name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
