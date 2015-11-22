package com.springapp.hardware_store.model;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "price_items")
public class PriceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "product_price")
    private double price;
    @Column(name = "modification_date")
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
