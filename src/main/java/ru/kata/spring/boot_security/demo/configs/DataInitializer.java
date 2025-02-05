package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

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
