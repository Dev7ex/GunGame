package com.dev7ex.gungame.location;

import com.dev7ex.common.bukkit.plugin.service.PluginService;
import com.dev7ex.gungame.GunGamePlugin;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
public class LocationService implements PluginService {

    private final Map<String, Location> locations = new HashMap<>();
    private LocationConfiguration configuration;

    public LocationService(final LocationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void onEnable() {
        this.locations.putAll(this.configuration.getSavedLocations());

        GunGamePlugin.getInstance().getLogger().info("Location Cache [" + this.locations.values().size() + "]");
    }

    @Override
    public void onDisable() {
        this.locations.clear();
    }

    public void cacheLocation(final String name, final Location location) {
        this.locations.put(name, location);
    }

    public Optional<Location> getCachedLocation(final String name) {
        return Optional.ofNullable(this.locations.get(name));
    }

}
