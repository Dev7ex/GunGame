package com.dev7ex.gungame.equipment;

import com.dev7ex.common.bukkit.configuration.ConfigurationBase;
import com.dev7ex.common.bukkit.configuration.ConfigurationProperties;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@ConfigurationProperties(fileName = "equipment.yml")
public class EquipmentConfiguration extends ConfigurationBase {

    public EquipmentConfiguration(@NotNull final Plugin plugin) {
        super(plugin);
        super.createFile();
    }

    public void createDefaults() {
        super.getFileConfiguration().addDefault("max-level", "1");
        super.getFileConfiguration().addDefault("equipments.0.weapon", "WOODEN_AXE");
        super.getFileConfiguration().addDefault("equipments.0.helmet", "LEATHER_HELMET");
        super.getFileConfiguration().addDefault("equipments.0.chestplate", "LEATHER_CHESTPLATE");
        super.getFileConfiguration().addDefault("equipments.0.leggings", "LEATHER_LEGGINGS");
        super.getFileConfiguration().addDefault("equipments.0.boots", "LEATHER_BOOTS");

        super.getFileConfiguration().addDefault("equipments.1.weapon", "WOODEN_SWORD");
        super.getFileConfiguration().addDefault("equipments.1.helmet", "LEATHER_HELMET");
        super.getFileConfiguration().addDefault("equipments.1.chestplate", "LEATHER_CHESTPLATE");
        super.getFileConfiguration().addDefault("equipments.1.leggings", "LEATHER_LEGGINGS");
        super.getFileConfiguration().addDefault("equipments.1.boots", "LEATHER_BOOTS");
        super.getFileConfiguration().options().copyDefaults(true);
        super.saveFile();
    }

    public Map<Integer, Equipment> getSavedEquipments() {
        final Map<Integer, Equipment> equipments = new HashMap<>();

        for (final String level : Objects.requireNonNull(super.getFileConfiguration().getConfigurationSection("equipments")).getKeys(false)) {
            final String path = "equipments." + level;
            final Equipment equipment = new Equipment(Integer.parseInt(level));
            equipment.setWeapon(new ItemStack(Material.valueOf(super.getFileConfiguration().getString(path + ".weapon"))));
            equipment.setHelmet(new ItemStack(Material.valueOf(super.getFileConfiguration().getString(path + ".helmet"))));
            equipment.setChestplate(new ItemStack(Material.valueOf(super.getFileConfiguration().getString(path + ".chestplate"))));
            equipment.setLeggings(new ItemStack(Material.valueOf(super.getFileConfiguration().getString(path + ".leggings"))));
            equipment.setBoots(new ItemStack(Material.valueOf(super.getFileConfiguration().getString(path + ".boots"))));
            equipments.put(Integer.parseInt(level), equipment);
        }
        return equipments;
    }

}
