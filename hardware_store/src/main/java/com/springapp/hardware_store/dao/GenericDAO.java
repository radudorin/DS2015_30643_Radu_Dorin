package com.springapp.hardware_store.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by radud on 23/11/2015.
 */
public interface GenericDAO<T> {

    public void saveOrUpdate(T entity);

    public T delete(int id);

    public T findById(int id);

    public List<T> findAll();

    public T getByField(String fieldName, String fieldValue);


}