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
    private final PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;

    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder, RegistrationService registrationService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.registrationService = registrationService;
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void createUser(String userName, String lastName, String email,String password, Set<String> roleNames) {
        registrationService.saveUser(userName, lastName, email, password, roleNames);
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
    public void updateUser(User updateUser) {
        updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userDao.save(updateUser);
    }


}