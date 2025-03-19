package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Transactional
    public Role add(Role role) {
       return roleRepository.save(role);
    }
    @Transactional
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    public Set<Role> findRoleByIds(List<Long> ids) {
        return new HashSet<>(roleRepository.findAllById(ids));
    }

    public Role findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Role not found by ID " + id));
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException("Role not found by name " + name));
    }
    @Transactional
    public Role update(Long id, Role role) {
        Optional<Role> updatableRoleOptional = roleRepository.findById(id);
        if (updatableRoleOptional.isEmpty()) {
            throw new RuntimeException("Role not found with ID " + id);
        }
        Role updatableRole = updatableRoleOptional.get();
        updatableRole.setName(role.getName());
        return roleRepository.save(updatableRole);
    }
}
