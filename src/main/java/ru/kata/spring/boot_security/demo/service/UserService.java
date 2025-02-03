package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    public Optional<User> findByUsername(String username);

    void createUser(User user);

    List<User> readAllUser();

    User readUserById(Long id);

    void updateUser(Long id, User updateUser);

    void deleteUserById(Long id);
}
