package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersByRole(Role role) {
        return role.getUsers();
    }

    @Override
    @Transactional
    public void createUser(User user) {

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(List.of(new Role(2L, "ROLE_USER")));
        }

        user.setPassword(passwordEncoder
                .encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(List.of(new Role(2L, "ROLE_USER")));
        }

        user.setPassword(passwordEncoder
                .encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
