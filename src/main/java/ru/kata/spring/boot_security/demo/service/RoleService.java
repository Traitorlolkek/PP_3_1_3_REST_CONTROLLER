package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;

public interface RoleService {
    public Optional<Role> findByName(String name);
    public void saveRole(Role role);
}
