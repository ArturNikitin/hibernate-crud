package services;

import dao.EmployeeDAO;
import model.Employee;

import javax.persistence.EntityManager;

public class EmployeeService {


    private EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public boolean checkEmployeePresenceByEmail(String email){
        Employee employee1 = employeeDAO.findEmployeeByEmail(email);
        return employee1 != null;
    }
}
