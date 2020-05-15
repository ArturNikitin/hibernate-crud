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


        EmployeeDAO employeeDAO = new EmployeeDAOImpl(manager);


        Employee employee = employeeDAO.findById(3);
        System.out.println(employee);

        manager.close();

        EntityManager manager1 = factory.createEntityManager();
        EmployeeDAO employeeDAO1 = new EmployeeDAOImpl(manager1);

        Employee employee1 = employeeDAO1.findById(3);
        System.out.println(employee1);

        manager1.close();
        factory.close();

    }
}
