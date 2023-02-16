package com.dev7ex.gungame.api.user;

import java.util.UUID;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public interface GunGameUser {

    UUID getUniqueId();

    String getName();

    GunGameUserConfiguration getConfiguration();

    void setConfiguration(final GunGameUserConfiguration configuration);

    boolean isSpectator();

    void setSpectator(final boolean spectator);

    boolean isBuildMode();

    void setBuildMode(final boolean buildMode);

    int getLevel();

    void setLevel(final int level);

    void decreaseLevel();

    void increaseLevel();

    int getKills();

    void setKills(final int kills);

    void increaseKills();

    int getDeaths();

    void setDeaths(final int deaths);

    void increaseDeaths();

    int getKillStreak();

    void setKillStreak(final int killStreak);

}
