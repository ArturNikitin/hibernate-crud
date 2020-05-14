package dao;

import model.Employee;
import model.Role;

import java.util.List;

public interface RoleDAO {
    Role createRole(String roleName);
    Role findRoleByName(String roleName);
    void removeRole(Role role);
    Role updateRoleName(Role role, String roleName);
    List<Role> findAllRoles();
}
