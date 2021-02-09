package com.edenstower.api.services;

import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private Environment env;

    public void addUser(User user){
        String keyProperty = env.getProperty("key");
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(keyProperty, 10000, 128);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
