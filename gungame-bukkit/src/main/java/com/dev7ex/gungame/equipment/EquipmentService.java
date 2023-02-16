package com.dev7ex.gungame.equipment;

import com.dev7ex.common.bukkit.plugin.service.PluginService;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
public class EquipmentService implements PluginService {

    private final Map<Integer, Equipment> equipments = new HashMap<>();
    private final EquipmentConfiguration configuration;

    public EquipmentService(final EquipmentConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void onEnable() {
        this.equipments.putAll(this.configuration.getSavedEquipments());
    }

    @Override
    public void onDisable() {
        this.equipments.clear();
    }

    public Equipment getEquipment(final int level) {
        return this.equipments.get(level);
    }

}
