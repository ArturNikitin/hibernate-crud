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

        new RoleDAOImpl(manager).findRoleByName("manager").getRoleName();



        manager.close();
        factory.close();

    }
}
