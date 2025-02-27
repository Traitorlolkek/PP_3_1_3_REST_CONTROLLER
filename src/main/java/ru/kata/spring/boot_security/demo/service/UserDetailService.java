package ru.kata.spring.boot_security.demo.service;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserDao userDao;

    public UserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User userRole = user.get();
        userRole.getUserRole().size();
        return user.get();
    }
}
