package com.edenstower.api.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "games")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    public enum Difficulty {
        Easy, Normal, Hard, Hell
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player", nullable = false)
    private User player;

    // Global Info
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "saved_at", nullable = false)
    private Date savedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", columnDefinition = "ENUM('EASY', 'NORMAL', 'HARD', 'HELL')", nullable = false)
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
}
