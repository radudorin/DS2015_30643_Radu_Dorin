package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.Transaction;
import com.springapp.hardware_store.model.TransactionStatus;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface TransactionStatusDAO {

    public void saveOrUpdate(TransactionStatus entity);

    public TransactionStatus delete(int id);

    public TransactionStatus findById(int id);

    public List<TransactionStatus> findAll();

    public TransactionStatus getByField(String fieldName, String fieldValue);

}
