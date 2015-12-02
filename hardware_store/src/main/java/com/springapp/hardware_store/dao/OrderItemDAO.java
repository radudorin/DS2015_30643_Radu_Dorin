package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.OrderItem;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface OrderItemDAO {

    public void saveOrUpdate(OrderItem entity);

    public OrderItem delete(int id);

    public OrderItem findById(int id);

    public List<OrderItem> findAll();

    public OrderItem getByField(String fieldName, String fieldValue);

}
