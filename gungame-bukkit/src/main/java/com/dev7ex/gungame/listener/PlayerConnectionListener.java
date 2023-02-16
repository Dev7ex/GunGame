package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserConfiguration;
import com.dev7ex.gungame.user.User;
import com.dev7ex.gungame.user.UserConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public class PlayerConnectionListener extends GunGameListener {

    public PlayerConnectionListener(@NotNull final GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final GunGameUser user = new User(player.getUniqueId(), player.getName());
        final GunGameUserConfiguration userConfiguration = new UserConfiguration(user);

        super.getUserProvider().registerUser(user);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        super.getUserProvider().unregisterUser(player.getUniqueId());
    }
}