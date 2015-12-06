package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.PriceItem;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface PriceItemDAO {

    public int save(PriceItem entity);

    public PriceItem delete(int id);

    public PriceItem findById(int id);

    public List<PriceItem> findAll();

    public PriceItem getByField(String fieldName, String fieldValue);

}
