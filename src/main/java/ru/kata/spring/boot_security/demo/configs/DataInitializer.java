package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleServiceImpl roleService;
    private final UserService userService;
    private final RegistrationService registrationService;

    public DataInitializer(RoleServiceImpl roleService, UserService userService, RegistrationService registrationService) {
        this.roleService = roleService;
        this.userService = userService;
        this.registrationService = registrationService;
    }


    @Override
    public void run(String[] args) throws Exception {

        if (roleService.findByName("ROLE_USER").isEmpty()) {
            roleService.saveRole(new Role("ROLE_USER"));
        }
        if (roleService.findByName("ROLE_ADMIN").isEmpty()) {
            roleService.saveRole(new Role("ROLE_ADMIN"));
        }

        if (userService.findByUsername("Admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("Admin");
            admin.setEmail("Admin@mail.ru");
            admin.setPassword("admin");
            registrationService.register(admin.getUsername(), admin.getPassword(), admin.getEmail(), Set.of("ROLE_ADMIN"));
        }

        if (userService.findByUsername("User").isEmpty()) {
            User user = new User();
            user.setUsername("User");
            user.setEmail("User@mail.ru");
            user.setPassword("user");
            registrationService.register(user.getUsername(), user.getPassword(), user.getEmail(), Set.of("ROLE_USER"));
        }
    }
}
