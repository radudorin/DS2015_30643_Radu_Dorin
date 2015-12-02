package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.TransactionStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class TransactionStatusDAOImpl implements TransactionStatusDAO {
    SessionFactory sessionFactory;

    public TransactionStatusDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(TransactionStatus transactionStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(transactionStatus);
    }

    @Override
    @Transactional
    public TransactionStatus delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        TransactionStatus transactionStatus = findById(id);
        session.delete(transactionStatus);
        return transactionStatus;
    }

    @Override
    @Transactional
    public TransactionStatus findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (TransactionStatus) session.get(TransactionStatus.class, id);
    }

    @Override
    @Transactional
    public List<TransactionStatus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(TransactionStatus.class);
        return crit.list();
    }

    @Override
    @Transactional
    public TransactionStatus getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        TransactionStatus transactionStatus = (TransactionStatus) session.createCriteria(TransactionStatus.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return transactionStatus;
    }
}
