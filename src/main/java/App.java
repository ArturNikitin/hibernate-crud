import model.Role;
import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("prodUnit");
        EntityManager manager = factory.createEntityManager();


        Role role = new Role("developer");
        Employee employee = new Employee("Arkadiy", "Mevkov","1@gmail.com", 34, role);
        manager.getTransaction().begin();

        try {
            manager.persist(role);
            manager.persist(employee);
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        }

        manager.getTransaction().commit();

        manager.close();
        factory.close();

    }
}
