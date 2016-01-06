package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.ShoppingCart;
import com.springapp.hardware_store.model.Subscription;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 24/12/2015.
 */
public class SubscriptionDAOImpl implements SubscriptionDAO {

    SessionFactory sessionFactory;

    public SubscriptionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(Subscription shoppingCart) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(shoppingCart);
    }

    @Override
    @Transactional
    public Subscription delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Subscription subscription = findById(id);
        session.delete(subscription);
        return subscription;
    }

    @Override
    @Transactional
    public Subscription findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Subscription) session.get(ShoppingCart.class, id);
    }

    @Override
    @Transactional
    public List<Subscription> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(Subscription.class);
        return crit.list();
    }

    @Override
    @Transactional
    public Subscription getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        Subscription subscription = (Subscription) session.createCriteria(ShoppingCart.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return subscription;
    }

    @Override
    @Transactional
    public void delete(int productId, int memberId) {
        Session session = sessionFactory.getCurrentSession();

        final Criteria criteria = session.createCriteria(Subscription.class)
                .createAlias("product", "p")
                .createAlias("member", "m")
                .add(Restrictions.eq("m.id", memberId))
                .add(Restrictions.eq("p.id", productId));

        Subscription subscription = (Subscription) criteria.uniqueResult();
        session.delete(subscription);
    }

    @Override
    @Transactional
    public List<Subscription> getSubscriptionsForProduct(int id) {
        Session session = sessionFactory.getCurrentSession();

        final Criteria criteria = session.createCriteria(Subscription.class)
                .createAlias("product", "p")
                .add(Restrictions.eq("p.id", id));

        List<Subscription> subscriptions = criteria.list();
        if (subscriptions.isEmpty()) {
            return null;
        }

        return subscriptions;
    }


}
