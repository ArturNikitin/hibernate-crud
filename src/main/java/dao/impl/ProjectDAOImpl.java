package dao.impl;

import dao.ProjectDAO;
import lombok.AllArgsConstructor;
import model.Employee;
import model.Project;
import model.utils.ProjectStatus;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@AllArgsConstructor
public class ProjectDAOImpl implements ProjectDAO {
    private EntityManager manager;

    @Override
    public Project createProject(String projectName, ProjectStatus status) {
        Project project = new Project(projectName, status);
        manager.getTransaction().begin();

        try {
            manager.persist(project);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }

        manager.getTransaction().commit();
        return project;
    }

    @Override
    public Project findByProjectName(String projectName) {
        try {
            return manager.createQuery("from Project p WHERE p.projectName = :searchName", Project.class)
                    .setParameter("searchName", projectName)
                    .getSingleResult();
        } catch (NoResultException exp) {
            return null;
        }
    }

    @Override
    public Project updateProjectStatus(Project project, ProjectStatus status) {
        manager.getTransaction().begin();

        try {
            project.setStatus(status);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }
        manager.getTransaction().commit();
        return project;
    }

    @Override
    public void removeProject(Project project) {
        manager.getTransaction().begin();

        try {
            manager.remove(project);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }
        manager.getTransaction().commit();
    }

    @Override
    public List<Project> findAllProjects() {
        try {
            return manager.createQuery("from Project", Project.class)
                    .getResultList();
        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Project> findAllProjectsByEmployee(Employee employee) {
        try {
            return manager.createQuery("from Project p join fetch p.employees c WHERE c.id = :searchId", Project.class)
                    .setParameter("searchId", employee.getId())
                    .getResultList();
        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }
    }
}
