package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.Rating;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface RatingDAO {

    public void saveOrUpdate(Rating entity);

    public Rating delete(int id);

    public Rating findById(int id);

    public List<Rating> findAll();

    public Rating getByField(String fieldName, String fieldValue);

}
