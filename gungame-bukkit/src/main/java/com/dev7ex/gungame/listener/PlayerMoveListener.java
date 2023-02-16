package com.dev7ex.gungame.listener;

import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.event.GunGameListener;
import com.dev7ex.gungame.user.User;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public class PlayerMoveListener extends GunGameListener {

    public PlayerMoveListener(@NotNull final GunGameApi gunGameApi) {
        super(gunGameApi);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerMove(final PlayerMoveEvent event) {
        if (event.getTo().getBlock().getType() != Material.WATER) {
            return;
        }
        final Player player = event.getPlayer();

        if (super.getUserProvider().getUser(player.getUniqueId()).isEmpty()) {
            return;
        }
        final User user = (User) super.getUserProvider().getUser(player.getUniqueId()).get();

        if ((user.isSpectator()) || (user.isBuildMode())) {
            return;
        }

        if (player.getKiller() == null) {
            player.sendMessage(super.getPrefix() + " §7Du bist gestorben");

        } else {
            final Player killer = player.getKiller();

            if (super.getUserProvider().getUser(killer.getUniqueId()).isEmpty()) {
                // Error message
                return;
            }
            final User killerUser = (User) super.getUserProvider().getUser(killer.getUniqueId()).get();

            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));

            killerUser.increaseKills();
            killerUser.increaseLevel();
            killer.playSound(killer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 1);

            killer.sendMessage(super.getPrefix() + "§7Du hast §a" + player.getName() + " §7getötet!");
            player.sendMessage(super.getPrefix() + "§7Du wurdest von §a" + killer.getName() + " §7getötet");

            killerUser.setEquipment(GunGamePlugin.getInstance().getEquipmentService().getEquipment(killerUser.getLevel()));
        }

        user.increaseDeaths();
        user.setLevel(0);

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25, 4));
        player.teleport(GunGamePlugin.getInstance().getLocationService().getCachedLocation("spawn").orElseThrow());
        player.setHealth(20D);
        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 1, 1);
        player.getInventory().clear();

        user.setEquipment(GunGamePlugin.getInstance().getEquipmentService().getEquipment(user.getLevel()));
    }

}
