package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

import java.util.Set;

@Controller
public class AuthorizationController {
    private final RegistrationService reg;
    @Autowired
    public AuthorizationController(RegistrationService reg) {
        this.reg = reg;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam Set<String> roles) {
        reg.register(username, password, email, roles);
        return "/login";
    }
}
