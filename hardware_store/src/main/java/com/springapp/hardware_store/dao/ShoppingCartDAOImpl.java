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
    public int save(ShoppingCart shoppingCart) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(shoppingCart);
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

    @Override
    @Transactional
    public ShoppingCart getShoppingCartForMember(int id) {
        Session session = sessionFactory.getCurrentSession();

        final Criteria criteria = session.createCriteria(ShoppingCart.class)
                .createAlias("member", "m")
                .add(Restrictions.eq("m.id", id));

        List<ShoppingCart> shoppingCarts = criteria.list();
        if (shoppingCarts.isEmpty()) {
            return null;
        }

        return shoppingCarts.get(0);
    }
}
