package com.edenstower.api.controllers;

import com.edenstower.api.entities.Game;
import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.GameRepository;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.GameService;
import com.edenstower.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<Game> getAllGames(){return gameRepository.findAll();}

    @GetMapping("/game")
    public Game getGame(@RequestParam String username, @RequestParam String slot){
        Game.SaveSlot saveSlot = Game.SaveSlot.valueOf(slot);
        return gameRepository.findByPlayerUsernameAndSaveSlot(username, saveSlot);
    }

    @PostMapping("/game")
    public ResponseEntity postGame(@RequestParam String username, @RequestParam String difficulty,
                                   @RequestParam boolean fullScreen, @RequestParam boolean autoSave,
                                   @RequestParam int gammaLvl, @RequestParam boolean sfxEnabled,
                                   @RequestParam int sfxLvl, @RequestParam boolean musicEnabled,
                                   @RequestParam int musicLvl, @RequestParam int strength,
                                   @RequestParam int vitality, @RequestParam int defense,
                                   @RequestParam int speed, @RequestParam int luck,
                                   @RequestParam long totalKills, @RequestParam long totalDeaths, @RequestParam long gameTimeInSeconds){

        User user = userRepository.findByUsername(username);

        if(user != null){
            int cantSaves = user.getGames().size();
            if(cantSaves < 4){
                Game game = new Game(
                        user,
                        new Date(),
                        new Date(),
                        cantSaves == 1? Game.SaveSlot.One: cantSaves == 2? Game.SaveSlot.Two: cantSaves == 3? Game.SaveSlot.Three: Game.SaveSlot.Four,
                        Game.Difficulty.valueOf(difficulty),
                        gameTimeInSeconds,
                        fullScreen,
                        autoSave,
                        gammaLvl,
                        sfxEnabled,
                        sfxLvl,
                        musicEnabled,
                        musicLvl,
                        strength,
                        vitality,
                        defense,
                        speed,
                        luck,
                        totalKills,totalDeaths
                );
                gameRepository.save(game);
                user.getGames().add(game);
                return  ResponseEntity.ok().body(game);
            }
        }
        return ResponseEntity.notFound().build();

    }

}
