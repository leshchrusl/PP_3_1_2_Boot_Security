package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);
    User findUserById(Long id);
    List<User> findAllUsers();
    List<User> findUsersByRole(Role role);
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
}
