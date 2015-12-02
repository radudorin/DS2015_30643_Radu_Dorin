package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.ShoppingCart;
import com.springapp.hardware_store.model.ShoppingCart;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class ShoppingCartDAOImpl implements ShoppingCartDAO {
    SessionFactory sessionFactory;

    public ShoppingCartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(ShoppingCart shoppingCart) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        ShoppingCart shoppingCart = findById(id);
        session.delete(shoppingCart);
        return shoppingCart;
    }

    @Override
    @Transactional
    public ShoppingCart findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (ShoppingCart) session.get(ShoppingCart.class, id);
    }

    @Override
    @Transactional
    public List<ShoppingCart> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(ShoppingCart.class);
        return crit.list();
    }

    @Override
    @Transactional
    public ShoppingCart getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.createCriteria(ShoppingCart.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return shoppingCart;
    }
}
