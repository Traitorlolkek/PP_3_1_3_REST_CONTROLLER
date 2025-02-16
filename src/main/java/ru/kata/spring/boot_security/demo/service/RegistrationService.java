package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final RoleDao roleDao;

    public RegistrationService(PasswordEncoder passwordEncoder, UserDao userDao, RoleDao roleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getUserRole()) {
            Role roleBase = roleDao.findByName(role.getName()).orElseThrow(() ->
                    new RuntimeException("Role '" + role + "' not found"));
            roles.add(roleBase);
        }
        user.setUserRole(roles);
        userDao.save(user);
        return user;
    }
}