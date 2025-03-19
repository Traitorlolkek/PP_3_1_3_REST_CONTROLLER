package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User add(User user) {
        String passEncoder = passwordEncoder.encode(user.getPassword());
        user.setPassword(passEncoder);
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.getById(id).getRoles().clear();
        userRepository.deleteById(id);
    }

    @Transactional
    public User update(Long id, User user) {
        Optional<User> updatableUserOptional = userRepository.findById(id);
        if (updatableUserOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID " + id);
        }
        User updatableUser = updatableUserOptional.get();
        updatableUser.setUsername(user.getUsername());
        updatableUser.setName(user.getName());
        updatableUser.setSurname(user.getSurname());
        updatableUser.setAge(user.getAge());
        updatableUser.setPassword(user.getPassword());
        updatableUser.setRoles(user.getRoles()); // Обновление ролей
        return userRepository.save(updatableUser);
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID " + id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }

    @Transactional
    public User setRole(Long roleId, Long userId) {
        Role role = roleRepository.getById(roleId);
        User user = userRepository.getById(userId);
        user.addRoleToUser(role);
        return user;
    }

    @Transactional
    public User deleteRole(Long roleId, Long userId) {
        Role role = roleRepository.getById(roleId);
        User user = userRepository.getById(userId);
        user.removeRoleToUser(role);
        return user;
    }
}
