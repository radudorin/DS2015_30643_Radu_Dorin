package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Product;
import com.springapp.hardware_store.model.Rating;
import com.springapp.hardware_store.model.Rating;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class RatingDAOImpl implements RatingDAO {
    SessionFactory sessionFactory;

    public RatingDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(Rating rating) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(rating);
    }

    @Override
    @Transactional
    public Rating delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Rating rating = findById(id);
        session.delete(rating);
        return rating;
    }

    @Override
    @Transactional
    public Rating findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Rating) session.get(Rating.class, id);
    }

    @Override
    @Transactional
    public List<Rating> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(Rating.class);
        return crit.list();
    }

    @Override
    @Transactional
    public Rating getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        Rating rating = (Rating) session.createCriteria(Rating.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return rating;
    }

    @Override
    @Transactional
    public List<Rating> findRatingsForProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        final Criteria criteria = session.createCriteria(Rating.class)
                .createAlias("product", "p")
                .add(Restrictions.eq("p.id", id));

        return criteria.list();
    }

    @Override
    @Transactional
    public List<Rating> findRatingsForMember(int id) {
        Session session = sessionFactory.getCurrentSession();
        final Criteria criteria = session.createCriteria(Rating.class)
                .createAlias("member", "m")
                .add(Restrictions.eq("m.id", id));

        return criteria.list();
    }


}
