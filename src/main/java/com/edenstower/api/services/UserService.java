package com.edenstower.api.services;

import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private Environment env;

    public User addUser(User user){
        String keyProperty = env.getProperty("key");
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(keyProperty, 10000, 128);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedAt(new Date());
        user.setLastLoggedAt(new Date());
        return userRepository.save(user);
    }

    public User editUser(User user){
        return userRepository.save(user);
    }
}
