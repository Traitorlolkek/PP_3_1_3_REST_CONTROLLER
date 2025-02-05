//package ru.kata.spring.boot_security.demo.configs;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RegistrationService;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class InitUser {
//    private final RegistrationService registrationService;
//
//    public InitUser(RegistrationService registrationService) {
//        this.registrationService = registrationService;
//    }
//
//    @PostConstruct
//    public void registerUser() {
//        User admin = new User("admin","admin","admin@gmail.com","root");
//        User user = new User("user","user","user@gmail.com","root");
//        Set<String> roles = new HashSet<>();
//        roles.add("ROLE_ADMIN");
//        Set<String> roles2 = new HashSet<>();
//        roles2.add("ROLE_USER");
//        registrationService.saveUser(admin,roles);
//        registrationService.saveUser(user,roles2);
//    }
//
//    public RegistrationService getRegistrationService() {
//        return registrationService;
//    }
//}
