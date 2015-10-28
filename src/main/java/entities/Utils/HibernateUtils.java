package entities.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by radud on 27/10/2015.
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null)
            sessionFactory= new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
