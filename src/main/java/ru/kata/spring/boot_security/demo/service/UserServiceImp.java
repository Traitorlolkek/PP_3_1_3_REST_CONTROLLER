package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService /*UserDetailsService*/ {
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void createUser(User user) {
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
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User updateUser) {
        Optional<User> existingUser = userDao.findById(id);
        if(existingUser.isPresent()){
            User user = existingUser.get();
            user.setName(updateUser.getName());
            user.setLast_name(updateUser.getLast_name());
            user.setEmail(updateUser.getEmail());
            return userDao.save(user);
        } else {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }

    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userDao.findByUsername(username);
//    }
}
