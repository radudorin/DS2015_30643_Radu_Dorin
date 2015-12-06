package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Order;
import com.springapp.hardware_store.model.OrderItem;

import java.util.List;

/**
 * Created by radud on 04/12/2015.
 */
public interface OrderDAO {

    public int save(Order entity);

    public Order delete(int id);

    public Order findById(int id);

    public List<Order> findAll();

    public Order getByField(String fieldName, String fieldValue);

    public List<Order> getOrdersForMember(int id);

}
