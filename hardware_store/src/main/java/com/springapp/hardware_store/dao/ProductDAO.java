package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.Product;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface ProductDAO {

    public int save(Product entity);

    public Product delete(int id);

    public Product findById(int id);

    public List<Product> findAll();

    public List<Product> findProductsForCategory(int id);

    public Product getByField(String fieldName, String fieldValue);

}
