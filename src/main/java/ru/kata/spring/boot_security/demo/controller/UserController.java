package ru.kata.spring.boot_security.demo.controller;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;



@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public String findUser(@AuthenticationPrincipal User user, Model model) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        model.addAttribute("user", newUser);
        return "adminPanel";
    }
}
