package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Product;
import com.springapp.hardware_store.model.ProductCategory;
import com.springapp.hardware_store.model.ProductCategory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class ProductCategoryDAOImpl implements ProductCategoryDAO {
    SessionFactory sessionFactory;

    public ProductCategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(ProductCategory productCategory) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(productCategory);
    }

    @Override
    @Transactional
    public ProductCategory delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductCategory productCategory = findById(id);
        session.delete(productCategory);
        return productCategory;
    }

    @Override
    @Transactional
    public ProductCategory findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (ProductCategory) session.get(ProductCategory.class, id);
    }

    @Override
    @Transactional
    public List<ProductCategory> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(ProductCategory.class);
        return crit.list();
    }

    @Override
    @Transactional
    public ProductCategory getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        ProductCategory productCategory = (ProductCategory) session.createCriteria(ProductCategory.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return productCategory;
    }
}
