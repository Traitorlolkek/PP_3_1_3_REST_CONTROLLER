package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    public void addUser(String username, String password, String email, Set<String> roleNames);
    public List<User> getAllUsers();
    public void removeUserById(Long id);
    public void updateUser(Long id, String username, String password, String email, Set<String> rolesNames);
    public User findUserById(Long id);
    Optional<User> findByUsername(String username);
}
