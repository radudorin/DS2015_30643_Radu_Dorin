package com.springapp.hardware_store.model;
import javax.persistence.*;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "price_items")
public class PriceItem {

    @Id
    @Column(name = "price_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "product_price")
    private double price;
    @Column(name = "modification_date")
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
