package com.edenstower.api.controllers;

import com.edenstower.api.entities.Game;
import com.edenstower.api.repositories.GameRepository;
import com.edenstower.api.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public List<Game> getAllGames(){return gameRepository.findAll();}

}
