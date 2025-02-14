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
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;

    public RegistrationService(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    @Transactional
    public void register(String username, String password, String email, Set<String> roleNames) {

        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleDao.findByName(roleName).orElseThrow(() ->
                    new RuntimeException("Role '" + roleName + "' not found"));
            roles.add(role);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(roles);
        userDao.save(user);
    }

}
