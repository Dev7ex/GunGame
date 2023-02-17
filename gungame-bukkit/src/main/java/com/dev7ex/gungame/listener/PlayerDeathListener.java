package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author DevSnx
 * @since 16.02.2023
 */
public class PlayerDeathListener extends GunGameListener {

    public PlayerDeathListener(@NotNull GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){



    }
}