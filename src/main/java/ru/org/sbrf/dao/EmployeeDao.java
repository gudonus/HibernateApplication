package ru.org.sbrf.dao;

import org.hibernate.Session;
import ru.org.sbrf.dto.Employee;
import ru.org.sbrf.dto.Function;
import ru.org.sbrf.exception.AddObjectException;
import ru.org.sbrf.hibernate.HibernateUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class EmployeeDao implements Dao<Employee> {

    private static Session session;

    public EmployeeDao() {
        session = HibernateUtil.getSession().openSession();
    }

    public void add(Employee object) throws AddObjectException {
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.clear();
    }

    public Employee get(long id) {
        return session.get(Employee.class, id);
    }

    public List<Employee> getAll() {
        CriteriaQuery<Employee> createQuery = session.getCriteriaBuilder().createQuery(Employee.class);
        createQuery.from(Employee.class);
        List<Employee> employees = session.createQuery(createQuery).getResultList();

        return employees;
    }

    public void delete(long id) {
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
        session.getTransaction().commit();
        session.clear();
    }

    public void update(Employee employee) {
        session.clear();

        session.beginTransaction();
        session.update(employee);
        session.getTransaction().commit();
    }

    public List<Function> getFunctions() {
        FunctionDao functionDao = new FunctionDao();

        return functionDao.getAll();
    }
}
