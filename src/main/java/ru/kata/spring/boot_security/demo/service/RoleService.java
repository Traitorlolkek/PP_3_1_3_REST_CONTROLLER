package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;
@Component
public class RoleService {
    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Optional<Role> findByName(String name) {
        return roleDao.findByName(name);
    }

    public void save(Role roleUser) {
        roleDao.save(roleUser);
    }
}
