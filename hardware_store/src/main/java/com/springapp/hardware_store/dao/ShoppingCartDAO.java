package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.ShoppingCart;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface ShoppingCartDAO {

    public void saveOrUpdate(ShoppingCart entity);

    public ShoppingCart delete(int id);

    public ShoppingCart findById(int id);

    public List<ShoppingCart> findAll();

    public ShoppingCart getByField(String fieldName, String fieldValue);

}
