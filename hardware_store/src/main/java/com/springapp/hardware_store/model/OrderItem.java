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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private ShoppingCart shoppingCart;
    @OneToMany
    @JoinColumn(name = "product_id", nullable = false)
    private List<Product> product;

    public OrderItem() {
    }

    public OrderItem(ShoppingCart shoppingCart, List<Product> product) {
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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
