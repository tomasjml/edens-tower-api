package com.edenstower.api.controllers;

import com.edenstower.api.entities.Game;
import com.edenstower.api.entities.GameID;
import com.edenstower.api.entities.User;
import com.edenstower.api.repositories.GameRepository;
import com.edenstower.api.repositories.UserRepository;
import com.edenstower.api.services.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @ApiOperation(value = "Method to get all the games")
    public List<Game> getAllGames(){return gameRepository.findAll();}

    @GetMapping("/game")
    @ApiOperation(value = "Method to GET a game based on the GameID(Username,Slot)")
    public ResponseEntity getGame(@RequestParam String username, @RequestParam String slot){

        Game.SaveSlot saveSlot = Game.SaveSlot.valueOf(slot);
        Map<String, String> response = new HashMap<>();
        if(!gameRepository.existsById(new GameID(username, saveSlot))){
            response.put("found", "false");
            response.put("message", "Game not found");
            return ResponseEntity.badRequest().body(response);
        }
        Optional<Game> game = gameRepository.findById(new GameID(username, saveSlot));
        return  ResponseEntity.ok().body(game);
    }

    @GetMapping("/username")
    @ApiOperation(value = "Method to GET all Games based on the GameID(Username)")
    public ResponseEntity getGameByUsername(@RequestParam String username){
        Map<String, String> response = new HashMap<>();
        if(!gameRepository.existsGameByGameIDPlayerUserName(username)){
            response.put("found", "false");
            response.put("message", "Game not found by Username: " + username);
            return ResponseEntity.badRequest().body(response);
        }
        List<Game> games = gameRepository.findGamesByGameIDPlayerUserName(username);
        return  ResponseEntity.ok().body(games);
    }

    @PostMapping("/game")
    @ApiOperation(value = "Method to save a game with all the required parameters")
    public ResponseEntity postGame(@RequestParam String username, @RequestParam String difficulty, @RequestParam boolean fullScreen, @RequestParam boolean autoSave,
                                   @RequestParam int gammaLvl, @RequestParam boolean sfxEnabled, @RequestParam int sfxLvl, @RequestParam boolean musicEnabled,
                                   @RequestParam int musicLvl, @RequestParam int strength, @RequestParam int vitality, @RequestParam int defense, @RequestParam int speed,
                                   @RequestParam int luck, @RequestParam long totalKills, @RequestParam long totalDeaths, @RequestParam long gameTimeInSeconds,
                                   @RequestParam String saveData, @RequestParam String saveSlotstr, @RequestParam Optional<Long> highScore){

        Map<String, String> response = new HashMap<>();
        User user = userRepository.findByUsername(username);
        if(user == null){
            response.put("posted", "false");
            response.put("message", "User " + username + " not found");
            return ResponseEntity.badRequest().body(response);
        }
        Game.SaveSlot saveSlot = Game.SaveSlot.valueOf(saveSlotstr);
        GameID gameID = new GameID(username, saveSlot);
        Optional<Game> oldGame = gameRepository.findById(gameID);

        if(oldGame.isEmpty()){
            int cantSaves = user.getGames().size();
            if(cantSaves < 4){
                Game game = new Game(
                        gameID,
                        user, saveData,
                        new Date(), new Date(),
                        Game.Difficulty.valueOf(difficulty),
                        gameTimeInSeconds, fullScreen,
                        autoSave, gammaLvl, sfxEnabled,
                        sfxLvl, musicEnabled, musicLvl,
                        strength, vitality, defense,
                        speed, luck, totalKills,totalDeaths, highScore.orElse((long)0)
                );
                gameRepository.save(game);
                //user.getGames().add(game);
                return  ResponseEntity.ok().body(game);
            }
        }else{
            Game newGame = new Game(
                    gameID, user, saveData,
                    oldGame.get().getCreatedAt(),
                    new Date(),
                    Game.Difficulty.valueOf(difficulty),
                    gameTimeInSeconds, fullScreen, autoSave,
                    gammaLvl, sfxEnabled, sfxLvl,
                    musicEnabled, musicLvl, strength,
                    vitality, defense, speed, luck,
                    totalKills, totalDeaths, highScore.orElse((long)0)
            );
            gameRepository.save(newGame);
            return  ResponseEntity.ok().body(newGame);
        }
        return ResponseEntity.notFound().build();

    }

//    @PutMapping("/game")
//    @ApiOperation(value = "Method to update the values of an existing game")
//    public ResponseEntity updateGame(@RequestParam String username, @RequestParam String difficulty, @RequestParam boolean fullScreen, @RequestParam boolean autoSave,
//                                     @RequestParam int gammaLvl, @RequestParam boolean sfxEnabled, @RequestParam int sfxLvl, @RequestParam boolean musicEnabled,
//                                     @RequestParam int musicLvl, @RequestParam int strength, @RequestParam int vitality, @RequestParam int defense, @RequestParam int speed,
//                                     @RequestParam int luck, @RequestParam long totalKills, @RequestParam long totalDeaths, @RequestParam long gameTimeInSeconds,
//                                     @RequestParam String saveData, @RequestParam String saveSlotstr){
//
//        Game.SaveSlot saveSlot = Game.SaveSlot.valueOf(saveSlotstr);
//        GameID gameID = new GameID(username, saveSlot);
//        Optional<Game> oldGame = gameRepository.findById(gameID);
//
//        if(oldGame.isPresent()){
//            User user = userRepository.findByUsername(username);
//            Game newGame = new Game(
//                    gameID, user, saveData,
//                    oldGame.get().getCreatedAt(),
//                    new Date(),
//                    Game.Difficulty.valueOf(difficulty),
//                    gameTimeInSeconds, fullScreen, autoSave,
//                    gammaLvl, sfxEnabled, sfxLvl,
//                    musicEnabled, musicLvl, strength,
//                    vitality, defense, speed, luck,
//                    totalKills, totalDeaths
//            );
//            gameRepository.save(newGame);
//            return  ResponseEntity.ok().body(newGame);
//        }
//        return ResponseEntity.notFound().build();
//
//    }

    @DeleteMapping("/game")
    @ApiOperation(value = "Method to delete a game given the GameID(Username,Slot)")
    public Map<String, String> deleteGame(@RequestParam String username, @RequestParam String saveSlotstr){
        Game.SaveSlot saveSlot = Game.SaveSlot.valueOf(saveSlotstr);
        Map<String, String> response = new HashMap<>();
        Optional<Game> game = gameRepository.findById(new GameID(username, saveSlot));
        if(game.isEmpty()){
            response.put("deleted", "false");
            response.put("message", "Game not found");
        }else{
            gameRepository.delete(game.get());
            response.put("deleted", "true");
            response.put("message", "Game has been deleted");
        }
        return response;
    }

}
