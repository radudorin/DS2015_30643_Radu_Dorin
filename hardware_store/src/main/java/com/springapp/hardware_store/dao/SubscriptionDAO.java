package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Subscription;

import java.util.List;

/**
 * Created by radud on 24/12/2015.
 */
public interface SubscriptionDAO {

    public int save(Subscription entity);

    public Subscription delete(int id);

    public Subscription findById(int id);

    public List<Subscription> findAll();

    public Subscription getByField(String fieldName, String fieldValue);

    public void delete(int productId, int memberId);

    public List<Subscription> getSubscriptionsForProduct(int id);

}
