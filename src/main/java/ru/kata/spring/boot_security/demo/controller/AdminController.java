package ru.kata.spring.boot_security.demo.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RegistrationService registrationService;

    public AdminController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("/users")
    public String getAllUser(Model model) {
        model.addAttribute("user", userService.readAllUser());
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/userForm";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String lastName,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam Set<String> roles) {
        registrationService.saveUser(username,lastName,email,password, roles);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.readUserById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam(required = false) Set<String> roles) {
        userService.updateUser(id, username,password,email, roles);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/find")
    public String findUserById(@RequestParam(value = "id", required = false) long id, Model model) {
        model.addAttribute("user", userService.readUserById(id));
        return "user";
    }
}
