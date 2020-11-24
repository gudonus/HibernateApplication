package ru.org.sbrf.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch(Throwable exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    public static SessionFactory getSession() {
        return sessionFactory;
    }

    public static void close() {
        getSession().close();
    }
}
