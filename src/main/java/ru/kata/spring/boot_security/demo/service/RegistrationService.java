package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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


    public void saveUser(String userName, String lastName, String email,String password, Set<String> roleNames) {
        User user = new User();
        user.setName(userName);
        user.setLast_name(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleDao.findByName(roleName).orElseThrow(() ->
                    new RuntimeException("Role '" + roleName + "' not found"));
            roles.add(role);
        }
        user.setUserRole(roles);

        userDao.save(user);
    }
}