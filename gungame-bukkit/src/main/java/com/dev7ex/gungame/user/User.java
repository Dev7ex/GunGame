package com.dev7ex.gungame.user;

import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserConfiguration;
import com.dev7ex.gungame.equipment.Equipment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class User implements GunGameUser {

    private final UUID uniqueId;
    private final String name;
    private GunGameUserConfiguration configuration;
    private boolean buildMode;
    private boolean spectator;
    private int level;
    private int kills;
    private int deaths;
    private int killStreak;

    public User(@NotNull final UUID uniqueId, @NotNull final String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    @Override
    public void setBuildMode(@NotNull final boolean buildMode) {
        if (buildMode) {
            this.getEntity().setGameMode(GameMode.CREATIVE);
            this.getEntity().getInventory().clear();
            this.buildMode = true;
            return;
        }
        this.getEntity().setGameMode(GameMode.ADVENTURE);
        this.setEquipment(GunGamePlugin.getInstance().getEquipmentService().getEquipment(this.level));
        this.buildMode = false;
    }

    @Override
    public void setSpectator(@NotNull final boolean spectator) {
        if (spectator) {
            this.getEntity().setGameMode(GameMode.CREATIVE);
            this.getEntity().getInventory().clear();
            Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(GunGamePlugin.getInstance(), this.getEntity()));
            this.spectator = true;
            return;
        }
        this.getEntity().setGameMode(GameMode.ADVENTURE);
        this.setEquipment(GunGamePlugin.getInstance().getEquipmentService().getEquipment(this.level));
        Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(GunGamePlugin.getInstance(), this.getEntity()));
        this.spectator = false;
    }

    @Override
    public void decreaseLevel() {
        if (this.level == 0) {
            return;
        }
        if (this.level != 1) {
            this.level = (int) Math.ceil((double) this.level / 2);

        } else {
            this.level--;
        }
        this.getEntity().setLevel(this.level);
    }

    @Override
    public void increaseLevel() {
        this.level++;
        this.getEntity().setLevel(this.level);
    }

    @Override
    public void increaseKills() {
        this.kills++;
    }

    @Override
    public void increaseDeaths() {
        this.deaths++;
    }

    public void setEquipment(@NotNull final Equipment equipment) {
        final PlayerInventory playerInventory = this.getEntity().getInventory();
        playerInventory.setItem(0, equipment.getWeapon());
        playerInventory.setHelmet(equipment.getHelmet());
        playerInventory.setChestplate(equipment.getChestplate());
        playerInventory.setLeggings(equipment.getLeggings());
        playerInventory.setBoots(equipment.getBoots());
    }

    public Player getEntity() {
        return Bukkit.getPlayer(this.uniqueId);
    }
}