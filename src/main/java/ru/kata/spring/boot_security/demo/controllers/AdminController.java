package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // Страница для создания и редактирования пользователя
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin/user-form";
    }

    // Обработка сохранения изменений в пользователе
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    // Создание нового пользователя
    @GetMapping("/create")
    public String createUserForm(Model model) {
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "admin/user-form";
    }

    // Обработка создания нового пользователя
    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    // Удаление пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
