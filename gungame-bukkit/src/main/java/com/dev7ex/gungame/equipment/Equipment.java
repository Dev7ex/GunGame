package com.dev7ex.gungame.equipment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Equipment {

    private final int level;
    private ItemStack weapon;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    public Equipment(final int level) {
        this.level = level;
    }

}
