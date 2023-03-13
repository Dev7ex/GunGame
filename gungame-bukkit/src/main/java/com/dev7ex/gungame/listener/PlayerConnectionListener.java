package com.dev7ex.gungame.listener;

import com.dev7ex.common.map.ParsedMap;
import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.api.user.GunGameUserConfiguration;
import com.dev7ex.gungame.api.user.GunGameUserProperty;
import com.dev7ex.gungame.user.User;
import com.dev7ex.gungame.user.UserConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
        final User user = new User(player.getUniqueId(), player.getName());
        final GunGameUserConfiguration userConfiguration = new UserConfiguration(user);
        final ParsedMap<GunGameUserProperty, Object> userData = userConfiguration.read();

        if (GunGamePlugin.getInstance().getLocationService().getCachedLocation("spawn").isEmpty()) {
            //Bukkit.broadcastMessage(super.getApiConfiguration().getString("no-spawn-set"));
            return;
        }

        if(GunGamePlugin.getInstance().getConfiguration().getBoolean("general.join-quit-messages") == true){
            event.setJoinMessage(GunGamePlugin.getInstance().getLanguageService().getString("player-join-game").replace("{0}", player.getName()));
        }

        player.teleport(GunGamePlugin.getInstance().getLocationService().getCachedLocation("spawn").get());
        player.setLevel(0);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setFoodLevel(20);

        user.setConfiguration(userConfiguration);
        user.setLevel(0);
        user.setKills(userData.getInteger(GunGameUserProperty.KILLS));
        user.setDeaths(userData.getInteger(GunGameUserProperty.DEATHS));
        user.setEquipment(GunGamePlugin.getInstance().getEquipmentService().getEquipment(user.getLevel()));

        super.getUserProvider().registerUser(user);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if(GunGamePlugin.getInstance().getConfiguration().getBoolean("general.join-quit-messages") == true){
            event.setQuitMessage(GunGamePlugin.getInstance().getLanguageService().getString("player-quit-game").replace("{0}", player.getName()));
        }

        super.getUserProvider().unregisterUser(player.getUniqueId());
    }
}