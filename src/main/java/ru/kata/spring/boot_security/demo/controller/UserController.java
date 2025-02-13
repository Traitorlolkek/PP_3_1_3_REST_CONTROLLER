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
    @ResponseBody
    public User findUser(@AuthenticationPrincipal User user, Model model) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setUserRole(user.getUserRole());
        model.addAttribute("user", newUser);
        System.out.println(newUser);
        return newUser;
    }
}
