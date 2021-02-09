package com.edenstower.api.configuration;

import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Date;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserService userService){
        return args -> {
            User user = new User(
                    "admin",
                    "admin",
                    "admin@edenstower.com",
                    new Date(),
                    "adminpowers",
                    User.Rol.Admin
            );
            userService.addUser(user);
        };
    }
}
