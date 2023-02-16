package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDropItemListener extends GunGameListener {

    public PlayerDropItemListener(@NotNull final GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        event.setCancelled(true);
    }
}
