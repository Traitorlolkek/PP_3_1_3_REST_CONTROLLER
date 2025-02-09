package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<User> findByUsername(String username);

    void createUser(String userName, String lastName, String email,String password, Set<String> roleNames);

    List<User> readAllUser();

    User readUserById(Long id);

    void updateUser(User updateUser, Set<String> roleNames);

    void deleteUserById(Long id);
}