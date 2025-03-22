package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);
    void delete(Long id);
    Role update(Long id, Role role);
    Role findById(Long id);
    List<Role> findAll();

}
