package dao;

import model.Role;

public interface RoleDAO {
    Role createRole(String roleName);
    Role findRoleByName(String roleName);
    void removeRole(Role role);
    Role updateRoleName(Role role, String roleName);
}
