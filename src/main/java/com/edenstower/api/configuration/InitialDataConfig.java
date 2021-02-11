package com.edenstower.api.configuration;

import com.edenstower.api.entities.Game;
import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.GameRepository;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.GameService;
import com.edenstower.api.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class InitialDataConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserService userService, UserRepository userRepository,
                                        GameService gameService, GameRepository gameRepository){
        return args -> {
            User user;
            Game game;
            if(userRepository.findById("admin").isEmpty()) {
                user = new User(
                        "admin",
                        "admin",
                        "admin",
                        "admin@edenstower.com",
                        new Date(),
                        "adminpowers",
                        User.Rol.Admin,
                        new Date()
                );
                userService.addUser(user);
            }
            if(userRepository.findById("client").isEmpty()){
                user = new User(
                        "client",
                        "client",
                        "client",
                        "client@edenstower.com",
                        new Date(),
                        "clientpowers",
                        User.Rol.Client,
                        new Date()
                );
                userService.addUser(user);
            }
            if(gameRepository.findById(1).isEmpty()){
                game = new Game(
                        userRepository.findByUsername("client"),
                        "{\"username\" : \"client\"}",
                        new Date(),
                        new Date(),
                        //Game.SaveSlot.One,
                        Game.Difficulty.Easy,
                        0,
                        true,
                        true,
                        6,
                        true,
                        100,
                        true,
                        100,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0
                );
                gameService.addGame(game);
            }
        };
    }
}
