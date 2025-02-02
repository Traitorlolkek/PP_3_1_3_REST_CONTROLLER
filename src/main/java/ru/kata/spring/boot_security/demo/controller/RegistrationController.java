package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // Обработка GET-запроса для отображения страницы регистрации
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/registration";
    }

    // Обработка POST-запроса для регистрации пользователя
    @PostMapping("/registration")
    public String registerUser(@RequestParam String username,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam Set<String> roles) {

        User user = new User();
        user.setUsername(username);
        user.setLast_name(lastName);
        user.setEmail(email);
        user.setPassword(password);

        // Сохраняем пользователя с ролями
        registrationService.saveUser(user, roles);

        // Перенаправляем на страницу входа
        return "redirect:/login";
    }
}
