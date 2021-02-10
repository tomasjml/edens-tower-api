package com.edenstower.api.serializers;

import com.edenstower.api.entities.Game;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GameSerialize implements JsonSerializer<Game> {

    @Override
    public JsonElement serialize(Game game, Type Game, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        String playerName = game.getPlayer().getUsername();
        jsonObject.addProperty("player", playerName);
        jsonObject.addProperty("createdAt", game.getCreatedAt().toString());
        jsonObject.addProperty("savedAt", game.getSavedAt().toString());
        jsonObject.addProperty("difficulty",game.getDifficulty().toString());
        jsonObject.addProperty("gameTimeInSeconds", game.getGameTimeInSeconds());
        jsonObject.addProperty("fullScreen",game.isFullScreen());
        jsonObject.addProperty("autoSave",game.isAutoSave());
        jsonObject.addProperty("gammaLvl",game.getGammaLvl());
        jsonObject.addProperty("sfxEnabled",game.isSfxEnabled());
        jsonObject.addProperty("sfxLvl",game.getSfxLvl());
        jsonObject.addProperty("musicEnabled",game.isMusicEnabled());
        jsonObject.addProperty("musicLvl",game.getMusicLvl());
        jsonObject.addProperty("strength",game.getStrength());
        jsonObject.addProperty("vitality",game.getVitality());
        jsonObject.addProperty("defense",game.getDefense());
        jsonObject.addProperty("speed",game.getSpeed());
        jsonObject.addProperty("luck",game.getLuck());
        jsonObject.addProperty("totalKills",game.getTotalKills());
        jsonObject.addProperty("totalDeaths",game.getTotalDeaths());


        return jsonObject;
    }
}
