package servlets;

import dao.impl.EmployeeDAOImpl;
import dao.impl.RoleDAOImpl;
import model.Role;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(LoginServlet.VERIFIED_EMAIL) != null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        List<Role> roles;
        try {
            roles = new RoleDAOImpl(manager).findAllRoles();
        } finally {
            manager.close();
        }

        RegistrationForm form = new RegistrationForm();
        form.setName("");
        form.setLastName("");
        form.setPassword("");
        form.setRoles(roles);
        form.setRole(roles.get(0));

        req.setAttribute("form", form);

        req.getRequestDispatcher("pages/reg.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String roleName = req.getParameter("role");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());

        try {
            new EmployeeDAOImpl(manager).createEmployee(name, lastName, email, password, new RoleDAOImpl(manager).findRoleByName(roleName));
        } finally {
            manager.close();
        }

        resp.sendRedirect("login?email=" + email);
    }
}
