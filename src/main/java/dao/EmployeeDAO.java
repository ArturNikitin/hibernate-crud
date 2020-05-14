package dao;

import model.Employee;
import model.Project;
import model.Role;

import java.util.List;

public interface EmployeeDAO {
    Employee createEmployee(String name, String lastName, String email, Role role);
    Employee findEmployeeByNameAndLastName(String name, String lastName);
    Employee findEmployeeByEmail(String email);
    Employee setEmployeeAddress(Employee employee, String country, String city, String street, String postcode);
    void removeEmployee(Employee employee);
    Employee updateEmployeeRole(Employee employee, Role role);
    Employee addProject(Employee employee, Project project);
    List<Employee> findAllEmployeesByRole(String roleName);
    List<Employee> getAllEmployees();
}
