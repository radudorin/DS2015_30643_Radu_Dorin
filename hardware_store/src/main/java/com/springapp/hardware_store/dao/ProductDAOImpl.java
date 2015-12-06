package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Product;
import com.springapp.hardware_store.model.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class ProductDAOImpl implements ProductDAO {
    SessionFactory sessionFactory;

    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(product);
    }

    @Override
    @Transactional
    public Product delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = findById(id);
        session.delete(product);
        return product;
    }

    @Override
    @Transactional
    public Product findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.get(Product.class, id);
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(Product.class);
        return crit.list();
    }

    @Override
    @Transactional
    public Product getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.createCriteria(Product.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return product;
    }

    @Override
    @Transactional
    public List<Product> findProductsForCategory(int id) {
        Session session = sessionFactory.getCurrentSession();

        final Criteria criteria = session.createCriteria(Product.class)
                .createAlias("category", "c")
                .add(Restrictions.eq("c.id", id));

        return criteria.list();
    }
}
