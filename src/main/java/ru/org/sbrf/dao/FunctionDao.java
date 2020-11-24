package ru.org.sbrf.dao;

import org.hibernate.Session;
import ru.org.sbrf.dto.Function;
import ru.org.sbrf.exception.AddObjectException;
import ru.org.sbrf.hibernate.HibernateUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FunctionDao implements Dao<Function> {

    private Session session;

    public FunctionDao() {
        session = HibernateUtil.getSession().openSession();
    }

    public void add(Function object) throws AddObjectException {
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.clear();
    }

    public Function get(long id) {
        return session.get(Function.class, id);
    }

    public List<Function> getAll() {
        CriteriaQuery<Function> createQuery = session.getCriteriaBuilder().createQuery(Function.class);
        createQuery.from(Function.class);
        List<Function> functions = session.createQuery(createQuery).getResultList();

        return functions;
    }

    public void delete(long id) {
        session.beginTransaction();
        Function function = session.get(Function.class, id);
        session.remove(function);
        session.getTransaction().commit();
        session.clear();
    }

    public void update(Function object) {
        session.clear();

        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }
}
