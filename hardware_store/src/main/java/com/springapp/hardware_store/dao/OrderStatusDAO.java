package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.OrderStatus;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface OrderStatusDAO {

    public int save(OrderStatus entity);

    public OrderStatus delete(int id);

    public OrderStatus findById(int id);

    public List<OrderStatus> findAll();

    public OrderStatus getByField(String fieldName, String fieldValue);

}
