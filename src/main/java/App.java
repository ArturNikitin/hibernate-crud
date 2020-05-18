import dao.EmployeeDAO;
import dao.ProjectDAO;
import dao.RoleDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.ProjectDAOImpl;
import dao.impl.RoleDAOImpl;
import model.Employee;
import model.Project;
import model.Role;
import model.utils.ProjectStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("prodUnit");
        EntityManager manager = factory.createEntityManager();


        EmployeeDAO employeeDAO = new EmployeeDAOImpl(manager);


        Employee employee = employeeDAO.findEmployeeByEmail("123@gmail");

        System.out.println(employeeDAO.validatePassword(employee, "123"));

        manager.close();
        factory.close();

    }
}
