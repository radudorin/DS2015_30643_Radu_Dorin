package com.springapp.hardware_store.model;

import javax.persistence.*;
/**
 * Created by radud on 22/11/2015.
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TransactionStatus transactionStatus;

    public Transaction() {
    }

    public Transaction(Member member, Product product, int quantity, TransactionStatus transactionStatus) {
        this.member = member;
        this.product = product;
        this.quantity = quantity;
        this.transactionStatus = transactionStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
