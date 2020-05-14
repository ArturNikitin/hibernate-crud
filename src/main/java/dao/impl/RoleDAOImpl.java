package dao.impl;

import dao.RoleDAO;
import lombok.AllArgsConstructor;
import model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@AllArgsConstructor
public class RoleDAOImpl implements RoleDAO {
    private EntityManager manager;

    @Override
    public Role createRole(String roleName) {
        Role role = new Role(roleName);
        manager.getTransaction().begin();

        try {
            manager.persist(role);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }
        manager.getTransaction().commit();
        return role;
    }

    @Override
    public Role findRoleByName(String roleName) {
        try {
            return manager.createQuery("from Role r WHERE r.roleName = :searchRoleName", Role.class)
                    .setParameter("searchRoleName", roleName)
                    .getSingleResult();
        } catch (NoResultException exp) {
            exp.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeRole(Role role) {
        manager.getTransaction().begin();

        try {
            manager.remove(role);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }

        manager.getTransaction().commit();
    }

    @Override
    public Role updateRoleName(Role role, String roleName) {
        manager.getTransaction().begin();

        try {
            role.setRoleName(roleName);
        } catch (Exception exp) {
            manager.getTransaction().rollback();
            throw exp;
        }

        manager.getTransaction().commit();
        return role;
    }

    @Override
    public List<Role> findAllRoles() {
        try {
            return manager.createQuery("from Role", Role.class)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
