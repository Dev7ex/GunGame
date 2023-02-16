package com.dev7ex.gungame.user;

import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class User implements GunGameUser {

    private final UUID uniqueId;
    private final String name;
    private int level;
    private int kills;
    private boolean buildMode;
    private boolean spectator;
    private GunGameUserConfiguration configuration;

    public User(final UUID uniqueId, final String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public Player getEntity() {
        return Bukkit.getPlayer(this.uniqueId);
    }
}