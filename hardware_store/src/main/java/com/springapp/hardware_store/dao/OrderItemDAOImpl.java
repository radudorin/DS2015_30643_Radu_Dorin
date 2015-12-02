package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.OrderItem;
import com.springapp.hardware_store.model.OrderItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class OrderItemDAOImpl implements OrderItemDAO {
    SessionFactory sessionFactory;

    public OrderItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(OrderItem orderItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderItem);
    }

    @Override
    @Transactional
    public OrderItem delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        OrderItem orderItem = findById(id);
        session.delete(orderItem);
        return orderItem;
    }

    @Override
    @Transactional
    public OrderItem findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (OrderItem) session.get(OrderItem.class, id);
    }

    @Override
    @Transactional
    public List<OrderItem> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(OrderItem.class);
        return crit.list();
    }

    @Override
    @Transactional
    public OrderItem getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        OrderItem orderItem = (OrderItem) session.createCriteria(OrderItem.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return orderItem;
    }
}
