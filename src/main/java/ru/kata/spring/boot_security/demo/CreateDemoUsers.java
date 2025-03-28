package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateDemoUsers implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateDemoUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        roleAdmin.add(new Role("ROLE_ADMIN"));
        roleUser.add(new Role("ROLE_USER"));
        User admin = new User("admin","adm Sec-name", (byte) 20,
                passwordEncoder.encode("admin"),
                "admin@mail.com");
        User user = new User("user", "user Sec-name", (byte) 20,
                passwordEncoder.encode("user"),
                "user@mail.com");

        admin.setRoles(roleAdmin);
        user.setRoles(roleUser);

        userRepository.save(user);
        userRepository.save(admin);
    }
}


