package ru.kata.spring.boot_security.demo.test_users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Init {

    private final UserService userService;
    private final RoleService roleService;
    User admin;
    User user1;
    User user2;
    Role adminRole;
    Role userRole;

    @Autowired
    public Init(UserService userService,
                RoleService roleService,
                PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {

        adminRole = new Role(1L, "ROLE_ADMIN");
        userRole = new Role(2L, "ROLE_USER");

        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        admin = new User("admin", "1234",
                1990, "admin@mail.ru", List.of(adminRole, userRole));

        user1 = new User("user1", "5678",
                1991, "user1@mail.ru", List.of(userRole));

        user2 = new User("user2", "1234",
                1992, "user2@mail.ru", List.of(userRole));

        userService.createUser(admin);
        userService.createUser(user1);
        userService.createUser(user2);
    }
}
