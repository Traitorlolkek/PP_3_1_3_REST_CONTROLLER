package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDao.save(role);
    }
}
