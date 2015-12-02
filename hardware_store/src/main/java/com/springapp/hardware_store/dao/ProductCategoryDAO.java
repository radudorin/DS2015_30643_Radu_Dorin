package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.ProductCategory;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface ProductCategoryDAO {

    public void saveOrUpdate(ProductCategory entity);

    public ProductCategory delete(int id);

    public ProductCategory findById(int id);

    public List<ProductCategory> findAll();

    public ProductCategory getByField(String fieldName, String fieldValue);

}
