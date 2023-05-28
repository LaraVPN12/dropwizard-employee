package org.example.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.domain.Employee;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeDAO extends AbstractDAO<Employee> {
    public EmployeeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Employee findById(Long id) {
        return get(id);
    }

    public List<Employee> findAll() {
        return list(namedQuery("Employee.findAll"));
    }

    public Employee create(Employee employee) {
        return persist(employee);
    }

    public Employee update(Employee employee) {
        return persist(employee);
    }

    public void delete(Employee employee) {
        currentSession().delete(employee);
    }
}
