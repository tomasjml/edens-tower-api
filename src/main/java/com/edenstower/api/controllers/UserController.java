package com.edenstower.api.controllers;

import com.edenstower.api.entities.Game;
import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.GameRepository;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    @ApiOperation(value = "Method to get all the users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    @ApiOperation(value = "Method to get a user given the username")
    public ResponseEntity<User> getUser(@RequestParam String username) {
        Optional<User> user = userRepository.findById(username);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user")
    @ApiOperation(value = "Method to Upload a user or update it if exists")
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
    @ApiOperation(value = "Method to delete a user given the username")
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

    @GetMapping("/highScore")
    @ApiOperation(value = "Method to GET the highscore of all Players")
    public Map<String, Long> getHighScore(){
        Map<String, Long> response = new HashMap<>();

        List<User> users = userRepository.findAll();
        List<Game> games;
        long highscore;

        for(User user: users){
            if(gameRepository.existsGameByGameIDPlayerUserName(user.getUsername())) {
                games = gameRepository.findGamesByGameIDPlayerUserName(user.getUsername());
                highscore = games.get(0).getHighScore();
                for (Game game : games) {
                    if (game.getHighScore() > highscore) {
                        highscore = game.getHighScore();
                    }
                }
                response.put(user.getUsername(), highscore);
            }
        }
        return response;
    }

}
