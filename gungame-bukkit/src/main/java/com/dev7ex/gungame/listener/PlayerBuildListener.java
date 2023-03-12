package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.api.user.GunGameUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 12.03.2023
 */
public class PlayerBuildListener extends GunGameListener {

    public PlayerBuildListener(@NotNull final GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerBreakBlock(final BlockBreakEvent event) {
        final Player player = event.getPlayer();

        if (super.getUserProvider().getUser(player.getUniqueId()).isEmpty()) {
            return;
        }
        final GunGameUser user = super.getUserProvider().getUser(player.getUniqueId()).get();

        event.setCancelled(!user.isBuildMode());
        player.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("no-break"));
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerPlaceBlock(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();

        if (super.getUserProvider().getUser(player.getUniqueId()).isEmpty()) {
            return;
        }
        final GunGameUser user = super.getUserProvider().getUser(player.getUniqueId()).get();

        event.setBuild(!user.isBuildMode());
        player.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("no-break"));
    }

}
