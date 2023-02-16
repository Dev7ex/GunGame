package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserProperty;
import com.dev7ex.gungame.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDeathListener extends GunGameListener {

    public PlayerDeathListener(@NotNull GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){

        if(!(event.getEntity() instanceof Player)){
            return;
        }

        Player player = event.getEntity().getPlayer();
        GunGameUser player_user = super.getUserProvider().getUser(player.getUniqueId()).orElseThrow();

        Player killer = event.getEntity().getKiller();
        GunGameUser killer_user = super.getUserProvider().getUser(killer.getUniqueId()).orElseThrow();


        if(killer instanceof  Player){

            killer_user.setDeaths(killer_user.getDeaths()+1);
            super.getUserProvider().saveUser(killer_user, GunGameUserProperty.KILLS);

            player_user.setDeaths(player_user.getDeaths()+1);
            super.getUserProvider().saveUser(player_user, GunGameUserProperty.DEATHS);

        }


    }


}