package servlets;

import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import model.Employee;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static final String VERIFIED_EMAIL = "verifiedEmail";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(VERIFIED_EMAIL) != null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }
        req.getRequestDispatcher("pages/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(manager);

        Employee employee = null;
        boolean verified = false;
        try {
            employee = employeeDAO.findEmployeeByEmail(email);
            if (employee != null) {
                verified = employeeDAO.validatePassword(employee, password);
            }
        } finally {
            manager.close();
        }

        if (employee != null && verified) {
            req.getSession().setAttribute(VERIFIED_EMAIL, email);
            resp.sendRedirect("profile");
        } else {
            resp.sendRedirect("login?email=" + email);
        }
    }
}
