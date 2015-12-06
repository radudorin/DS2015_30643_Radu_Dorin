package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.CartItem;
import com.springapp.hardware_store.model.OrderItem;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface CartItemDAO {

    public int save(CartItem entity);

    public CartItem delete(int id);

    public CartItem findById(int id);

    public List<CartItem> findAll();

    public CartItem getByField(String fieldName, String fieldValue);

}
