package services;

import dao.EmployeeDAO;
import model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class EmployeeServiceTest {
    @Mock
    private EmployeeDAO dao;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.employeeService = new EmployeeService(dao);
    }

    @Test
    void checkEmployeePresenceByEmailEmployeeExists() {
        given(dao.findEmployeeByEmail("employee@gmail.com")).willReturn(new Employee());
        assertTrue(employeeService.checkEmployeePresenceByEmail("employee@gmail.com"));

        Mockito.verify(dao, Mockito.times(1)).findEmployeeByEmail("employee@gmail.com");
    }

    @Test
    void checkEmployeePresenceByEmailEmployeeDoesNotExist() {
        given(dao.findEmployeeByEmail(anyString())).willReturn(null);
        assertFalse(employeeService.checkEmployeePresenceByEmail("employee@gmail.com"));
    }
}