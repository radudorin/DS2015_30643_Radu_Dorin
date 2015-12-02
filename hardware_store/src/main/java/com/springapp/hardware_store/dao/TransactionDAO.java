package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.Transaction;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface TransactionDAO {

    public void saveOrUpdate(Transaction entity);

    public Transaction delete(int id);

    public Transaction findById(int id);

    public List<Transaction> findAll();

    public Transaction getByField(String fieldName, String fieldValue);

}
