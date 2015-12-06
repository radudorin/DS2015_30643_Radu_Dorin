package com.springapp.hardware_store.dao;

import com.springapp.hardware_store.model.Member;
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
@SuppressWarnings("unchecked")
public class MemberDAOImpl implements MemberDAO {

    SessionFactory sessionFactory;

    public MemberDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(Member member) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(member);
    }

    @Override
    @Transactional
    public Member delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Member member = findById(id);
        session.delete(member);
        return member;
    }

    @Override
    @Transactional
    public Member findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Member) session.get(Member.class, id);
    }

    @Override
    @Transactional
    public List<Member> findAll() {
        Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(Member.class);
        return crit.list();
    }

    @Override
    @Transactional
    public Member getByField(String fieldName, String fieldValue) {
        Session session = sessionFactory.getCurrentSession();
        Member member = (Member) session.createCriteria(Member.class).
                add(Restrictions.eq(fieldName, fieldValue)).
                uniqueResult();
        return member;
    }
}
