package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

import java.util.Set;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/registration";
    }


    @PostMapping("/registration")
    public String registerUser(@RequestParam String username,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam Set<String> roles) {
        registrationService.saveUser(username,lastName,email,password, roles);
        return "redirect:/login";
    }
}
