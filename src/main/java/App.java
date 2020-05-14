import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.RoleDAOImpl;
import model.Employee;
import model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("prodUnit");
        EntityManager manager = factory.createEntityManager();



        manager.close();
        factory.close();

    }
}
