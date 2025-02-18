    package ru.kata.spring.boot_security.demo.controller;


    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import ru.kata.spring.boot_security.demo.model.User;
    import ru.kata.spring.boot_security.demo.service.RegistrationService;
    import ru.kata.spring.boot_security.demo.service.UserService;

    import java.util.List;


    @RestController
    @RequestMapping("/admin")
    public class AdminController {
        private final UserService userService;
        private final RegistrationService registrationService;

        public AdminController(UserService userService, RegistrationService registrationService) {
            this.userService = userService;
            this.registrationService = registrationService;
        }

        @GetMapping("/")
        public List<User> getAllUser() {
            return userService.readAllUser();
        }


        @PostMapping("/add")
        public ResponseEntity<User> addUser(@RequestBody User user) {
            User newUser = registrationService.saveUser(user);
            return ResponseEntity.ok(newUser);
        }

        @GetMapping("edit/{id}")
        public User updateUser(@PathVariable("id") Long id) {
            return userService.readUserById(id);
        }

        @PutMapping("edit")
        public ResponseEntity<User> updateUser(@RequestBody User user) {
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        }

        @DeleteMapping("delete/{id}")
        public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        }
    }
