package com.springapp.hardware_store.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 23/11/2015.
 */
@SuppressWarnings("unchecked")
public class GenericDAOImpl<T> implements GenericDAO<T> {

    private SessionFactory sessionFactory;
    private Class<T> type;

    public GenericDAOImpl(Class<T> type, SessionFactory sessionFactory) {
        this.type = type;
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public T delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        T entity = findById(id);
        session.delete(entity);
        return entity;
    }

    @Override
    @Transactional
    public T findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(type, id);
    }

    @Override
    @Transactional
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(type);
        return crit.list();
    }

    @Override
    @Transactional
    public T getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.createCriteria(type).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();

        return type.cast(obj);
    }
}

