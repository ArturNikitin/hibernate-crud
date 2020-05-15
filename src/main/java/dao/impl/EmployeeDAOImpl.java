package dao.impl;

import dao.EmployeeDAO;
import lombok.AllArgsConstructor;
import model.Employee;
import model.Project;
import model.Role;
import model.utils.EmployeeAddress;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.rmi.server.ExportException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO {
    private EntityManager manager;

    @Override
    public Employee createEmployee(String name, String lastName, String email, Role role) {
        Employee employee = new Employee(name, lastName, email, role);

        manager.getTransaction().begin();
        try {
            manager.persist(employee);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }
        manager.getTransaction().commit();

        return employee;
    }

    @Override
    public Employee findEmployeeByNameAndLastName(String name, String lastName) {
        try {
            return manager.createQuery("from Employee e WHERE e.name = :searchName and e.lastName = :searchLastName", Employee.class)
                    .setParameter("searchName", name)
                    .setParameter("searchLastName", lastName)
                    .getSingleResult();
        } catch (NoResultException exp) {
            return null;
        }
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        try {
            return manager.createQuery("from Employee WHERE email = :searchEmail", Employee.class)
                    .setParameter("searchEmail", email)
                    .getSingleResult();
        } catch (NoResultException exp) {
            return null;
        }
    }

    @Override
    public Employee setEmployeeAddress(Employee employee, String country, String city, String street, String postcode) {
        manager.getTransaction().begin();
        try {
            employee.setAddress(new EmployeeAddress(country, city, street, postcode));
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }
        manager.getTransaction().commit();
        return employee;
    }

    @Override
    public void removeEmployee(Employee employee) {
        manager.getTransaction().begin();
        try {
            manager.remove(employee);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }
        manager.getTransaction().commit();
    }

    @Override
    public Employee updateEmployeeRole(Employee employee, Role role) {
        manager.getTransaction().begin();
        try {
            employee.setRole(role);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
        return employee;
    }

    @Override
    public Employee addProject(Employee employee, Project project) {
        manager.getTransaction().begin();
        try {
            List<Project> projects = employee.getProjects();
            projects.add(project);
            employee.setProjects(projects);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Employee> findAllEmployeesByRole(String roleName) {

            try {
                return manager.createQuery("Select e from Employee e join fetch e.role as r WHERE r.roleName = :searchName", Employee.class)
                        .setParameter("searchName", roleName)
                        .getResultList();
            } catch (Exception exp) {
                exp.printStackTrace();
                return null;
            }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return manager.createQuery("from Employee", Employee.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
