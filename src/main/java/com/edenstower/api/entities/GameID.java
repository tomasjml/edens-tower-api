package com.edenstower.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class GameID implements Serializable {

    private String playerUserName;
    private Game.SaveSlot saveSlot;

    public GameID(String playerUserName, Game.SaveSlot saveslot) {
        this.playerUserName = playerUserName;
        this.saveSlot = saveslot;
    }
}
