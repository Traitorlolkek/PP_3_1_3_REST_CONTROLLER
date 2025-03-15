package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(int id, User user) {
        User oldUser = userRepository.getById(id);
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
