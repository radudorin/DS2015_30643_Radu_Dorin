package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.PriceItem;
import com.springapp.hardware_store.model.PriceItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class PriceItemDAOImpl implements PriceItemDAO {
    SessionFactory sessionFactory;

    public PriceItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(PriceItem priceItem) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(priceItem);
    }

    @Override
    @Transactional
    public PriceItem delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        PriceItem priceItem = findById(id);
        session.delete(priceItem);
        return priceItem;
    }

    @Override
    @Transactional
    public PriceItem findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (PriceItem) session.get(PriceItem.class, id);
    }

    @Override
    @Transactional
    public List<PriceItem> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(PriceItem.class);
        return crit.list();
    }

    @Override
    @Transactional
    public PriceItem getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        PriceItem priceItem = (PriceItem) session.createCriteria(PriceItem.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return priceItem;
    }

    @Override
    @Transactional
    public List<PriceItem> getPriceItemsForProduct(int id) {
        Session session = sessionFactory.getCurrentSession();

        final Criteria criteria = session.createCriteria(PriceItem.class)
                .createAlias("product", "p")
                .add(Restrictions.eq("p.id", id));

        List<PriceItem> priceItems = criteria.list();
        if (priceItems.isEmpty()) {
            return null;
        }

        return priceItems;
    }
}
