package com.springapp.hardware_store.model;
import javax.persistence.*;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "product_category", uniqueConstraints = {
        @UniqueConstraint(columnNames = "category_name") })
public class ProductCategory {

    @Id
    @Column(name = "product_category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "category_name")
    private String name;
    @OneToMany
    @JoinColumn(name="product_category_id")
    List<Product> products;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
