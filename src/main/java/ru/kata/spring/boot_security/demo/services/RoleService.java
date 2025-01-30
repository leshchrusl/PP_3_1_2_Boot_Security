package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface RoleService {
    Role findRoleByName(String roleName);
    List<Role> findAllRoles();
    List<Role> findUserRoles(User user);
    void saveRole(Role role);
    void deleteRole(Role role);
}
