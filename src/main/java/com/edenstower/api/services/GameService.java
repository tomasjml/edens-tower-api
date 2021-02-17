package com.edenstower.api.services;

import com.edenstower.api.entities.Game;
import com.edenstower.api.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    final GameRepository gameRepository;

    public Game addGame(Game game){
        return gameRepository.save(game);
    }
}
