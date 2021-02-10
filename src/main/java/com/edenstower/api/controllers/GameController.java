package com.edenstower.api.controllers;

import com.edenstower.api.entities.Game;
import com.edenstower.api.repositories.GameRepository;
import com.edenstower.api.serializers.GameSerialize;
import com.edenstower.api.services.GameService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public List<String> getAllGames(){
        Gson gson = new GsonBuilder().registerTypeAdapter(Game.class, new GameSerialize()).create();

        List<Game> gameList = gameRepository.findAll();
        //String aux;
        ArrayList<String> jsonList = new ArrayList<>();

        for (Game game: gameList) {
            jsonList.add(gson.toJson(game));
        }

        return jsonList;
    }
//    @GetMapping("/")
//    public List<Game> getAllGames(){return gameRepository.findAll();}

}
