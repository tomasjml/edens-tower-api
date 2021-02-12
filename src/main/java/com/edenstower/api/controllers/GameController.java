package com.edenstower.api.controllers;

import com.edenstower.api.entities.Game;
import com.edenstower.api.entities.GameID;
import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.GameRepository;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Optional<Game> getGame(@RequestParam String username, @RequestParam String slot){
        Game.SaveSlot saveSlot = Game.SaveSlot.valueOf(slot);
        return gameRepository.findById(new GameID(username, saveSlot));
    }

    @PostMapping("/game")
    public ResponseEntity postGame(@RequestParam String username, @RequestParam String difficulty,
                                   @RequestParam boolean fullScreen, @RequestParam boolean autoSave,
                                   @RequestParam int gammaLvl, @RequestParam boolean sfxEnabled,
                                   @RequestParam int sfxLvl, @RequestParam boolean musicEnabled,
                                   @RequestParam int musicLvl, @RequestParam int strength,
                                   @RequestParam int vitality, @RequestParam int defense,
                                   @RequestParam int speed, @RequestParam int luck,
                                   @RequestParam long totalKills, @RequestParam long totalDeaths,
                                   @RequestParam long gameTimeInSeconds, @RequestParam String saveData,
                                   @RequestParam String saveSlotstr){

        User user = userRepository.findByUsername(username);
        Game.SaveSlot saveSlot = Game.SaveSlot.valueOf(saveSlotstr);
        if(!gameRepository.existsById(new GameID(username, saveSlot))){
            int cantSaves = user.getGames().size();
            if(cantSaves < 4){
                Game game = new Game(
                        new GameID(username, saveSlot),
                        user,
                        saveData,
                        new Date(),
                        new Date(),
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
                //user.getGames().add(game);
                return  ResponseEntity.ok().body(game);
            }
        }
        return ResponseEntity.notFound().build();

    }

}
