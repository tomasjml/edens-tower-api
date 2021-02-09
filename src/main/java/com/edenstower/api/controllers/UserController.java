package com.edenstower.api.controllers;

import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        if (!userRepository.existsByUsername(username)) {
            User user = new User(
                    username,
                    firstName,
                    lastName,
                    email,
                    password,
                    rol.equals("Admin") ? User.Rol.Admin : User.Rol.Client
            );
            return userService.addUser(user);
        } else {
            // Send empty attributes if not gonna be edited
            User oldUser = userRepository.findByUsername(username);
            User user = new User(
                    username,
                    firstName.equals("") ? oldUser.getFirstName() : firstName,
                    lastName.equals("") ? oldUser.getLastName() : lastName,
                    email.equals("") ? oldUser.getEmail() : email,
                    oldUser.getPassword(),
                    rol.equals("Admin") ? User.Rol.Admin : User.Rol.Client
            );
            user.setCreatedAt(oldUser.getCreatedAt());
            user.setLastLoggedAt(oldUser.getLastLoggedAt());
            return userService.editUser(user);
        }
    }

    @DeleteMapping("/user")
    public Map<String, String> deleteUser(@RequestParam String username) {
        Map<String, String> response = new HashMap<>();
        if (!userRepository.existsByUsername(username)) {
            response.put("deleted", "false");
            response.put("message", "User " + username  + " not found");
        } else {
            User oldUser = userRepository.findByUsername(username);
            userRepository.delete(oldUser);
            response.put("deleted", "true");
            response.put("message", "User " + username  + " has been deleted");
        }
        return response;
    }

}
