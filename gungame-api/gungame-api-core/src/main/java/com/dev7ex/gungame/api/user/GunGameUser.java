package com.dev7ex.gungame.api.user;

import java.util.UUID;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public interface GunGameUser {

    UUID getUniqueId();

    int getKills();

    int getDeaths();

    String getName();

    GunGameUserConfiguration getConfiguration();

    void setConfiguration(final GunGameUserConfiguration configuration);

    boolean isSpectator();

    void setSpectator(final boolean spectator);

    boolean isBuildMode();

    void setBuildMode(final boolean buildMode);

    void setKills(final int kills);

    void setDeaths(final int deaths);
}
