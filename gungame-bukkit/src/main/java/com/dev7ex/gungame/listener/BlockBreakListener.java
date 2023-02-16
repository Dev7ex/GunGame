package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author DevSnx
 * @since 16.02.2023
 */
public class BlockBreakListener extends GunGameListener {

    public BlockBreakListener(@NotNull final GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){

        Player player = event.getPlayer();
        Block block = event.getBlock();

        event.setCancelled(true);
        player.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("no-break"));

    }
}