package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role add(Role role) {
        return roleRepository.findByName(role.getName())
                .orElseGet(() -> roleRepository.save(role));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found by ID " + id));
    }

    @Override
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
