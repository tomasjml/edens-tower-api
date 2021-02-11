package com.edenstower.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "games")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Game implements Serializable {

    public enum SaveSlot{
        One, Two, Three, Four
    }

    public enum Difficulty {
        Easy, Normal, Hard, Hell
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player", nullable = false)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User player;

    @Type(type = "json")
    @Column(name = "save_data", columnDefinition = "json")
    private String saveData;

    // Global Info
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "saved_at", nullable = false)
    private Date savedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="save_slot", columnDefinition = "ENUM('One','Two','Three','Four')", nullable = false)
    @NotNull
    private SaveSlot saveSlot;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", columnDefinition = "ENUM('Easy', 'Normal', 'Hard', 'Hell')", nullable = false)
    @NotNull
    private Difficulty difficulty;

    @Column(name = "game_time_in_seconds", columnDefinition = "integer default 0")
    @NotNull
    private long gameTimeInSeconds;

    // Graphics
    @Column(name = "full_screen", nullable = false, columnDefinition = "boolean default true")
    @NotNull
    private boolean fullScreen;

    //Save Properties
    @Column(name = "auto_save", nullable = false, columnDefinition = "boolean default true")
    @NotNull
    private boolean autoSave;

    // Settings Info
    @Column(name = "gamma_lvl", nullable = false, columnDefinition = "integer default 6")
    @NotNull
    private int gammaLvl;

    @Column(name = "sfx_enabled", nullable = false, columnDefinition = "boolean default true")
    @NotNull
    private boolean sfxEnabled;

    @Column(name = "sfx_lvl", nullable = false, columnDefinition = "integer default 100")
    @NotNull
    private int sfxLvl;

    @Column(name = "music_enabled", nullable = false, columnDefinition = "boolean default true")
    @NotNull
    private boolean musicEnabled;

    @Column(name = "music_lvl", nullable = false, columnDefinition = "integer default 100")
    @NotNull
    private int musicLvl;

    // Player Stats
    @Column(name = "strength", nullable = false, columnDefinition = "integer default 0")
    @NotNull
    private int strength;

    @Column(name = "vitality", nullable = false, columnDefinition = "integer default 0")
    @NotNull
    private int vitality;

    @Column(name = "defense", nullable = false, columnDefinition = "integer default 0")
    @NotNull
    private int defense;

    @Column(name = "speed", nullable = false, columnDefinition = "integer default 0")
    @NotNull
    private int speed;

    @Column(name = "luck", nullable = false, columnDefinition = "integer default 0")
    @NotNull
    private int luck;

    // Game stats
    @Column(name = "total_kills", columnDefinition = "integer default 0")
    @NotNull
    private long totalKills;

    @Column(name = "total_deaths", columnDefinition = "integer default 0")
    @NotNull
    private long totalDeaths;

    public Game(User player, Date createdAt, Date savedAt, SaveSlot saveSlot, Difficulty difficulty, long gameTimeInSeconds, boolean fullScreen, boolean autoSave, int gammaLvl, boolean sfxEnabled, int sfxLvl, boolean musicEnabled, int musicLvl, int strength, int vitality, int defense, int speed, int luck, long totalKills, long totalDeaths) {
        this.player = player;
        this.createdAt = createdAt;
        this.savedAt = savedAt;
        this.saveSlot = saveSlot;
        this.difficulty = difficulty;
        this.gameTimeInSeconds = gameTimeInSeconds;
        this.fullScreen = fullScreen;
        this.autoSave = autoSave;
        this.gammaLvl = gammaLvl;
        this.sfxEnabled = sfxEnabled;
        this.sfxLvl = sfxLvl;
        this.musicEnabled = musicEnabled;
        this.musicLvl = musicLvl;
        this.strength = strength;
        this.vitality = vitality;
        this.defense = defense;
        this.speed = speed;
        this.luck = luck;
        this.totalKills = totalKills;
        this.totalDeaths = totalDeaths;
    }

    public Game(User player, String saveData,Date createdAt, Date savedAt, SaveSlot saveSlot, Difficulty difficulty, long gameTimeInSeconds, boolean fullScreen, boolean autoSave, int gammaLvl, boolean sfxEnabled, int sfxLvl, boolean musicEnabled, int musicLvl, int strength, int vitality, int defense, int speed, int luck, long totalKills, long totalDeaths) {
        this.player = player;
        this.saveData = saveData;
        this.createdAt = createdAt;
        this.savedAt = savedAt;
        this.saveSlot = saveSlot;
        this.difficulty = difficulty;
        this.gameTimeInSeconds = gameTimeInSeconds;
        this.fullScreen = fullScreen;
        this.autoSave = autoSave;
        this.gammaLvl = gammaLvl;
        this.sfxEnabled = sfxEnabled;
        this.sfxLvl = sfxLvl;
        this.musicEnabled = musicEnabled;
        this.musicLvl = musicLvl;
        this.strength = strength;
        this.vitality = vitality;
        this.defense = defense;
        this.speed = speed;
        this.luck = luck;
        this.totalKills = totalKills;
        this.totalDeaths = totalDeaths;
    }
}
