package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> readAllUser();

    User readUserById(Long id);

    User updateUser(Long id, User updateUser);

    void deleteUserById(Long id);
}
