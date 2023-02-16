package com.dev7ex.gungame.location;

import com.dev7ex.common.bukkit.configuration.ConfigurationBase;
import com.dev7ex.common.bukkit.configuration.ConfigurationProperties;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@ConfigurationProperties(fileName = "location.yml")
public class LocationConfiguration extends ConfigurationBase {

    public LocationConfiguration(@NotNull final Plugin plugin) {
        super(plugin);
    }

    public void saveLocation(final String name, final Location location) {
        super.getFileConfiguration().set("locations." + name + ".world-name", location.getWorld().getName());
        super.getFileConfiguration().set("locations." + name + ".x", location.getX());
        super.getFileConfiguration().set("locations." + name + ".y", location.getY());
        super.getFileConfiguration().set("locations." + name + ".z", location.getZ());
        super.getFileConfiguration().set("locations." + name + ".yaw", location.getYaw());
        super.getFileConfiguration().set("locations." + name + ".pitch", location.getPitch());
        super.saveFile();
    }

    public Location getSavedLocation(final String name) {
        final String worldName = super.getFileConfiguration().getString("locations." + name + ".world-name");
        final int x = super.getFileConfiguration().getInt("locations." + name + ".x");
        final int y = super.getFileConfiguration().getInt("locations." + name + ".y");
        final int z = super.getFileConfiguration().getInt("locations." + name + ".z");
        final float yaw = (float) super.getFileConfiguration().getDouble("locations." + name + ".yaw");
        final float pitch = (float) super.getFileConfiguration().getDouble("locations." + name + ".pitch");
        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    public Map<String, Location> getSavedLocations() {
        final Map<String, Location> locations = new HashMap<>();

        if (super.getFileConfiguration().getConfigurationSection("locations") == null) {
            return Collections.emptyMap();
        }
        super.getFileConfiguration().getConfigurationSection("locations").getKeys(false).forEach(name -> {
            locations.put(name, this.getSavedLocation(name));
        });
        return locations;
    }

}
