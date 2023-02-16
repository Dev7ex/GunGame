package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author DevSnx
 * @since 16.02.2023
 */
public class PlayerPickUpItemListener extends GunGameListener {

    public PlayerPickUpItemListener(@NotNull final GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler
    public void onPlayerPickUpItem(PlayerPickupItemEvent event){
        event.setCancelled(true);
    }
}