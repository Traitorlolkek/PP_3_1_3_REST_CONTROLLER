package ru.kata.spring.boot_security.demo.controller;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;



@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public User findUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/user")
    public String getUserProfile() {
        return "userPanel";
    }

}
