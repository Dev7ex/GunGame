package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.user.User;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public class PlayerDamageListener extends GunGameListener {

    public PlayerDamageListener(@NotNull final GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerDamage(final EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return; // Check if Entity is a Player
        }
        final Player player = (Player) event.getEntity();

        if (event.getFinalDamage() <= player.getHealth()) {
            return; // Check if the player would be killed by the damage
        }

        if (super.getUserProvider().getUser(player.getUniqueId()).isEmpty()) {
            //TODO Add Error Message (Should actually never occur)
            return;
        }
        final User user = (User) super.getUserProvider().getUser(player.getUniqueId()).get();

        if ((user.isSpectator()) || (user.isBuildMode())) {
            event.setCancelled(true);
            return;
        }
        user.increaseDeaths();
        user.decreaseLevel();

        if (player.getKiller() == null) {
            player.sendMessage(super.getPrefix() + " §7Du bist gestorben");
            return;
        }

        if (super.getUserProvider().getUser(player.getKiller().getUniqueId()).isEmpty()) {
            //TODO Add Error Message (Should actually never occur)
            return;
        }
        final Player killer = player.getKiller();
        final User userKiller = (User) super.getUserProvider().getUser(player.getKiller().getUniqueId()).get();

        if ((userKiller.isBuildMode()) || (userKiller.isSpectator())) {
            player.sendMessage(super.getPrefix() + " §7Du bist gestorben");
        }

        killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));
        killer.playSound(killer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 1);
        killer.sendMessage(super.getPrefix() + "§7Du hast " + player.getName() + " §7getötet");
        player.sendMessage(super.getPrefix() + "§7Du wurdest von " + killer.getName() + " §7getötet");

        userKiller.increaseKills();
        userKiller.increaseLevel();
        userKiller.setEquipment(GunGamePlugin.getInstance().getEquipmentService().getEquipment(userKiller.getLevel()));

        player.teleport(GunGamePlugin.getInstance().getLocationService().getCachedLocation("spawn").orElseThrow());
        player.setHealth(20D);
        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25, 4));
        player.getInventory().clear();
        user.setEquipment(GunGamePlugin.getInstance().getEquipmentService().getEquipment(userKiller.getLevel()));
    }

}