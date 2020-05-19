package servlets;

import dao.impl.EmployeeDAOImpl;
import dao.impl.ProjectDAOImpl;
import model.Employee;
import model.Project;
import org.hibernate.annotations.common.util.impl.Log;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileForm form = new ProfileForm();

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        String email = (String) req.getSession().getAttribute(LoginServlet.VERIFIED_EMAIL);
        Employee employee;
        List<Project> projects;
        try {
             employee = new EmployeeDAOImpl(manager).findEmployeeByEmail(email);
             projects = new ProjectDAOImpl(manager).findAllProjectsByEmployee(employee);
        } finally {
            manager.close();
        }

        form.setName(employee.getName());
        form.setLastName(employee.getLastName());
        if (projects != null) {
            form.setProjects(projects);
        }
        req.setAttribute("form", form);
        req.getRequestDispatcher("pages/profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute(LoginServlet.VERIFIED_EMAIL);
        resp.sendRedirect(req.getContextPath());
    }
}
