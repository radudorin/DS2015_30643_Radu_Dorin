package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Order;
import com.springapp.hardware_store.model.Order;
import com.springapp.hardware_store.model.ShoppingCart;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 04/12/2015.
 */
public class OrderDAOImpl implements OrderDAO {

    SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(order);
    }

    @Override
    @Transactional
    public Order delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = findById(id);
        session.delete(order);
        return order;
    }

    @Override
    @Transactional
    public Order findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Order) session.get(Order.class, id);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(Order.class);
        return crit.list();
    }

    @Override
    @Transactional
    public Order getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        Order order = (Order) session.createCriteria(Order.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return order;
    }

    @Override
    @Transactional
    public List<Order> getOrdersForMember(int id) {
        Session session = sessionFactory.getCurrentSession();

        final Criteria criteria = session.createCriteria(Order.class)
                .createAlias("member", "m")
                .add(Restrictions.eq("m.id", id));

        List<Order> orders = criteria.list();
        if (orders.isEmpty()) {
            return null;
        }

        return orders;
    }

}
