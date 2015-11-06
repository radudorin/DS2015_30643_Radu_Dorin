package entities.DAO;

/**
 * Created by radud on 27/10/2015.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by cristi on 10/14/2015.
 */
public class EntityDAO<T> {
    private static final Log LOGGER = LogFactory.getLog(EntityDAO.class);

    private SessionFactory factory;

    public EntityDAO(SessionFactory factory) {
        this.factory = factory;
    }


    public boolean saveOrUpdate(T entity) {
        boolean isSaved = false;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            isSaved = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return isSaved;
    }

    public T delete(int id, Class<T> type) {

        Session session = factory.openSession();
        Transaction tx = null;
        T entity = findById(id, type);
        if (entity != null) {
            try {
                tx = session.beginTransaction();
                session.delete(entity);
                session.flush();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                session.close();
            }
        }
        return entity;
    }

    public T findById(int id, Class<T> type) {
        Session session = factory.openSession();
        Transaction tx = null;
        T entity = null;
        try {
            tx = session.beginTransaction();
            entity = session.get(type, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return entity;
    }

    public List<T> findAll(Class<T> type) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<T> entities = null;
        try {
            tx = session.beginTransaction();
            final Criteria crit = session.createCriteria(type);
            entities = crit.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return entities;
    }

    public T getByField(Class<T> type, String fieldName, String fieldValue) {
        Session session = factory.openSession();
        Transaction tx = null;
        T entity = null;
        try {
            tx = session.beginTransaction();
            Object obj = session.createCriteria(type).
                    add(Restrictions.eq(fieldName, fieldValue)).
                    uniqueResult();
            entity = type.cast(obj);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return entity;
    }
}
