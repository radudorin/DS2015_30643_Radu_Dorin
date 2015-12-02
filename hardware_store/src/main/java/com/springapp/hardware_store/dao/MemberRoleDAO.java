package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.MemberRole;

import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public interface MemberRoleDAO {

    public void saveOrUpdate(MemberRole entity);

    public MemberRole delete(int id);

    public MemberRole findById(int id);

    public List<MemberRole> findAll();

    public MemberRole getByField(String fieldName, String fieldValue);

}
