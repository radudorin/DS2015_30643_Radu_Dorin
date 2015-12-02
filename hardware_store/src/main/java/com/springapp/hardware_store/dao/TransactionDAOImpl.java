package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Transaction;
import com.springapp.hardware_store.model.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class TransactionDAOImpl implements TransactionDAO {
    SessionFactory sessionFactory;

    public TransactionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(transaction);
    }

    @Override
    @Transactional
    public Transaction delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = findById(id);
        session.delete(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public Transaction findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Transaction) session.get(Transaction.class, id);
    }

    @Override
    @Transactional
    public List<Transaction> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(Transaction.class);
        return crit.list();
    }

    @Override
    @Transactional
    public Transaction getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = (Transaction) session.createCriteria(Transaction.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return transaction;
    }
}
