package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void createUser(String userName, String lastName, String email, String password, Set<String> roleNames) {
        User user = new User();
        user.setUsername(userName);
        user.setLast_name(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readAllUser() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User readUserById(Long id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User userForUpdate = userDao.findById(user.getId()).orElseThrow();
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setLast_name(user.getLast_name());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getUserRole()) {
            Role roleBase = roleDao.findByName(role.getName()).orElseThrow(() ->
                    new RuntimeException("Role '" + role + "' not found"));
            roles.add(roleBase);
        }
        userForUpdate.setUserRole(roles);
        return userDao.save(userForUpdate);
    }
}