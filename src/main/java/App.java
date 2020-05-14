import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.RoleDAOImpl;
import model.Employee;
import model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("prodUnit");
        EntityManager manager = factory.createEntityManager();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(manager);
        RoleDAO roleDAO = new RoleDAOImpl(manager);

        Role role = roleDAO.createRole("developer");
        Employee employee = employeeDAO.createEmployee("Ivan", "Ivanov", "1@email.com", role);

        System.out.println(employeeDAO.findEmployeeByEmail("1@email.com"));
        System.out.println(roleDAO.findRoleByName("developer"));
    }
}
