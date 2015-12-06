package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
import com.springapp.hardware_store.model.MemberRole;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by radud on 02/12/2015.
 */
public class MemberRoleDAOImpl implements MemberRoleDAO {
    SessionFactory sessionFactory;

    public MemberRoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(MemberRole memberRole) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(memberRole);
    }

    @Override
    @Transactional
    public MemberRole delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        MemberRole memberRole = findById(id);
        session.delete(memberRole);
        return memberRole;
    }

    @Override
    @Transactional
    public MemberRole findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (MemberRole) session.get(MemberRole.class, id);
    }

    @Override
    @Transactional
    public List<MemberRole> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(MemberRole.class);
        return crit.list();
    }

    @Override
    @Transactional
    public MemberRole getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        MemberRole memberRole = (MemberRole) session.createCriteria(MemberRole.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return memberRole;
    }
}
