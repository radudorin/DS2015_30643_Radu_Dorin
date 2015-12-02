package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.OrderStatus;
import com.springapp.hardware_store.model.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class OrderStatusDAOImpl implements OrderStatusDAO {
    SessionFactory sessionFactory;

    public OrderStatusDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(OrderStatus orderStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderStatus);
    }

    @Override
    @Transactional
    public OrderStatus delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        OrderStatus orderStatus = findById(id);
        session.delete(orderStatus);
        return orderStatus;
    }

    @Override
    @Transactional
    public OrderStatus findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (OrderStatus) session.get(OrderStatus.class, id);
    }

    @Override
    @Transactional
    public List<OrderStatus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(OrderStatus.class);
        return crit.list();
    }

    @Override
    @Transactional
    public OrderStatus getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        OrderStatus orderStatus = (OrderStatus) session.createCriteria(OrderStatus.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return orderStatus;
    }
}
