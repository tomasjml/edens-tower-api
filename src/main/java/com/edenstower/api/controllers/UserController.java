package com.edenstower.api.controllers;

import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String username) {
        Optional<User> user = userRepository.findById(username);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user")
    public User postUser(@RequestParam String username, @RequestParam String firstName,
                         @RequestParam String lastName, @RequestParam String email,
                         @RequestParam String password, @RequestParam String rol) {
        User user = new User(
                username,
                firstName,
                lastName,
                email,
                password,
                rol.equals("Admin") ? User.Rol.Admin : User.Rol.Client
        );
        return userService.addUser(user);
    }
}
