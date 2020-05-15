import dao.EmployeeDAO;
import dao.ProjectDAO;
import dao.RoleDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.ProjectDAOImpl;
import dao.impl.RoleDAOImpl;
import model.Employee;
import model.Project;
import model.utils.ProjectStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("prodUnit");
        EntityManager manager = factory.createEntityManager();

        ProjectDAO projectDAO = new ProjectDAOImpl(manager);
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(manager);
        RoleDAO roleDAO = new RoleDAOImpl(manager);

        Employee employee = employeeDAO.findEmployeeByEmail("123@");

        System.out.println(projectDAO.findAllProjectsByEmployee(employee));


        manager.close();
        factory.close();

    }
}
