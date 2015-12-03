package com.springapp.hardware_store.model;
import javax.persistence.*;
import java.util.List;

/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private ShoppingCart shoppingCart;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem() {
    }

    public OrderItem(ShoppingCart shoppingCart, Product product) {
        this.shoppingCart = shoppingCart;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
