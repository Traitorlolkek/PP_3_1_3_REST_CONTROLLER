package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Role extends JpaRepository<Role,Long> {
}
