package dao.impl;

import dao.EmployeeDAO;
import model.Employee;
import model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servlets.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOImplTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private EmployeeDAO employeeDAO;

    @BeforeEach
    void setUp() {
        factory = Persistence.createEntityManagerFactory("TestUnit");
        manager = factory.createEntityManager();
        employeeDAO = new EmployeeDAOImpl(manager);
    }

    @AfterEach
    void tearDown() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    void createEmployeeTest() {
        String name = "Ivan";
        String lastName = "Ivanov";
        String email = "1@email.com";
        String password = "123";
        Role role = new Role("role");

        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();


        Employee employee = employeeDAO.createEmployee(name, lastName, email, password, role);

        assertNotNull(employee);
        assertEquals(employee.getName(),name);
        assertEquals(employee.getLastName(), lastName);
        assertEquals(employee.getEmail(), email);
        assertEquals(employee.getRole().getRoleName(), role.getRoleName());
    }

    @Test
    void createEmployeeSameEmailTEst() {
        String name = "Ivan";
        String lastName = "Ivanov";
        String email = "1@email.com";
        String password = "123";
        Role role = new Role("role");

        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();


        employeeDAO.createEmployee(name, lastName, email, password, role);

        assertThrows(RollbackException.class, () -> employeeDAO.createEmployee("name", "lastName", email, password, role));
    }

    @Test
    void createEmployeeUnknownRoleTest() {
        String name = "Ivan";
        String lastName = "Ivanov";
        String email = "1@email.com";
        String password = "123";
        Role role = new Role("role");

        assertThrows(IllegalStateException.class, () -> employeeDAO.createEmployee(name, lastName, email, password, role));
    }

    @Test
    void findEmployeeByNameAndLastNameTest() {
        String name = "Ivan";
        String lastName = "Ivanov";
        String email = "1@email.com";
        String password = "123";
        Role role = new Role("role");
        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();
        Employee employee = new Employee(name, lastName, email, password, role);

        manager.getTransaction().begin();
        manager.persist(employee);
        manager.getTransaction().commit();


        Employee foundEmployee = employeeDAO.findEmployeeByEmail(email);

        assertNotNull(foundEmployee);
        assertEquals(name, foundEmployee.getName());
        assertEquals(lastName, foundEmployee.getLastName());
        assertEquals(email, foundEmployee.getEmail());
        assertEquals(password, foundEmployee.getPassword());
        assertEquals(role.getRoleName(), foundEmployee.getRole().getRoleName());
        assertNotEquals(0, employee.getId());
    }
}