package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.CartItem;
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
public class CartItemDAOImpl implements CartItemDAO {
    SessionFactory sessionFactory;

    public CartItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(CartItem orderItem) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(orderItem);
    }

    @Override
    @Transactional
    public CartItem delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        CartItem cartItem = findById(id);
        session.delete(cartItem);
        return cartItem;
    }

    @Override
    @Transactional
    public CartItem findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (CartItem) session.get(OrderItem.class, id);
    }

    @Override
    @Transactional
    public List<CartItem> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(OrderItem.class);
        return crit.list();
    }

    @Override
    @Transactional
    public CartItem getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        CartItem cartItem = (CartItem) session.createCriteria(OrderItem.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return cartItem;
    }
}
