package com.edenstower.api.configuration;

import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserService userService, UserRepository userRepository){
        return args -> {
            User user;
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
        };
    }
}
