package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleDao roleDao;
    private final UserService userService;
    private final RegistrationService registrationService;

    public DataInitializer(RoleDao roleDao, UserService userService, RegistrationService registrationService) {
        this.roleDao = roleDao;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @Override
    public void run(String[] args) throws Exception {
        if (roleDao.findByName("ROLE_USER").isEmpty()) {
            roleDao.save(new Role("ROLE_USER"));
        }
        if (roleDao.findByName("ROLE_ADMIN").isEmpty()) {
            roleDao.save(new Role("ROLE_ADMIN"));
        }
        if (userService.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setName("admin");
            admin.setLast_name("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword("root");
            registrationService.saveUser(admin, Set.of("ROLE_ADMIN"));
        }

        if (userService.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setLast_name("user");
            user.setEmail("user@user.com");
            user.setPassword("root");
            registrationService.saveUser(user, Set.of("ROLE_USER"));
        }
    }
}
