package com.edenstower.api.repositories;

import com.edenstower.api.entities.Game;
import com.edenstower.api.entities.GameID;
import com.edenstower.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, GameID> {
    public List<Game> findGamesByGameIDPlayerUserName(String username);
    public boolean existsGameByGameIDPlayerUserName(String username);
}
