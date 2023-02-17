package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
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
        new BukkitRunnable(){
            @Override
            public void run() {
                event.getEntity().getPlayer().spigot().respawn();
            }
        }.runTask(GunGamePlugin.getInstance());

        if(!(event.getEntity() instanceof Player)){
            return;
        }

        Player player = event.getEntity().getPlayer();
        GunGameUser player_user = super.getUserProvider().getUser(player.getUniqueId()).orElseThrow();

        Player killer = event.getEntity().getKiller();
        GunGameUser killer_user = super.getUserProvider().getUser(killer.getUniqueId()).orElseThrow();


        if(killer instanceof  Player){

            killer_user.setDeaths(killer_user.getDeaths()+1);
            killer_user.setLevel(killer_user.getLevel()+1);
            super.getUserProvider().saveUser(killer_user, GunGameUserProperty.KILLS, GunGameUserProperty.LEVEL);

            player_user.setDeaths(player_user.getDeaths()+1);
            player_user.setLevel(player_user.getLevel()-1);
            super.getUserProvider().saveUser(player_user, GunGameUserProperty.DEATHS, GunGameUserProperty.LEVEL);

        }


    }


}