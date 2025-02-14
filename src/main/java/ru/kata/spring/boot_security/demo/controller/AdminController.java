package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AdminController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("allUsers")
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", Set.of("USER", "ADMIN"));
        return "allUsers";
    }

    @PostMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("editUser/{id}")
    @ResponseBody
    public User editUserForm(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("editUser")
    public String editUser(@RequestParam Long id,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam Set<String> roles) {
        userService.updateUser(id, username, password, email, roles);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/add";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email,
                          @RequestParam Set<String> roles) {
        registrationService.register(username, password, email, roles);
        return "redirect:/admin/allUsers";
    }
}
