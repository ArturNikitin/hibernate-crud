import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.ProjectDAOImpl;
import model.Employee;
import model.Project;
import model.utils.ProjectStatus;
import services.EmployeeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("prodUnit");
        EntityManager manager = factory.createEntityManager();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(manager);

        EmployeeService employeeService = new EmployeeService(employeeDAO);
        System.out.println(employeeService.checkEmployeePresenceByEmail("123@gmail"));

        manager.close();
        factory.close();

    }
}
