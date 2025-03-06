package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService,
                           RoleService roleService,
                           PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getAdminHelloPage(Model model) {
        return "adminHello";
    }

    @GetMapping("/users")
    public String getAllUsersPage(Model model) {

        model.addAttribute("users", userService.findAllUsers());

        return "users";
    }

    @GetMapping("/add")
    public String getAddUserPage(Model model, User user) {

        model.addAttribute("user", user);

        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("listRoles") ArrayList<Long> roles) {

        user.setRoles(roleService.findByIdRoles(roles));
        userService.createUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/update")
    public String getUpdateUserPage(@PathVariable("id") Long id,
                                    Model model) {

        User user = userService.findUserById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);

        return "updateUser";
    }

    @PostMapping("{id}/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("listRoles") ArrayList<Long> roles) {

        user.setRoles(roleService.findByIdRoles(roles));

        userService.updateUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {

        if (userService.findUserById(id) == null) {
            return "redirect:/admin/users";
        }

        userService.deleteUserById(id);

        return "redirect:/admin/users";
    }
}
