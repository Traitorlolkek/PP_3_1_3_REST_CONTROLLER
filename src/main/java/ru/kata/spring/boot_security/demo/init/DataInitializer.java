package ru.kata.spring.boot_security.demo.init;

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


    public DataInitializer(RoleDao roleDao) {
        this.roleDao = roleDao;

    }

    @Override
    public void run(String[] args) throws Exception {
        if (roleDao.findByName("ROLE_USER").isEmpty()) {
            roleDao.save(new Role("ROLE_USER"));
        }
        if (roleDao.findByName("ROLE_ADMIN").isEmpty()) {
            roleDao.save(new Role("ROLE_ADMIN"));
        }
    }
}
