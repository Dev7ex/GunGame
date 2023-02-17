package com.dev7ex.gungame.user;

import com.dev7ex.common.map.ParsedMap;
import com.dev7ex.gungame.api.GunGameProvider;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserConfiguration;
import com.dev7ex.gungame.api.user.GunGameUserProperty;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public class UserConfiguration implements GunGameUserConfiguration {

    private final GunGameUser user;
    private File configurationFile;
    private YamlConfiguration fileConfiguration;

    @SneakyThrows
    public UserConfiguration(final GunGameUser user) {
        this.user = user;
        this.configurationFile = new File(GunGameProvider.getGunGameApi().getUserFolder()
                + File.separator + user.getUniqueId().toString() + ".yml");

        if (!this.configurationFile.exists()) {
            this.configurationFile.createNewFile();
        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.configurationFile);
        this.fileConfiguration.addDefault(GunGameUserProperty.UNIQUE_ID.getStoragePath(), user.getUniqueId().toString());
        this.fileConfiguration.addDefault(GunGameUserProperty.NAME.getStoragePath(), user.getName());
        this.fileConfiguration.addDefault(GunGameUserProperty.KILLS.getStoragePath(), 0);
        this.fileConfiguration.addDefault(GunGameUserProperty.DEATHS.getStoragePath(), 0);
        this.fileConfiguration.addDefault(GunGameUserProperty.KILLSTREAK.getStoragePath(), 0);
        this.fileConfiguration.options().copyDefaults(true);
        this.saveFile();
    }

    @Override
    public ParsedMap<GunGameUserProperty, Object> read() {
        final ParsedMap<GunGameUserProperty, Object> userData = new ParsedMap<>();

        Arrays.stream(GunGameUserProperty.values()).forEach(property -> {
            switch (property) {
                case UNIQUE_ID:
                    userData.put(property, UUID.fromString(Objects.requireNonNull(this.fileConfiguration.getString(property.getStoragePath()))));
                    break;

                case NAME:
                    userData.put(property, this.fileConfiguration.getString(property.getStoragePath()));
                    break;

                case KILLS: case DEATHS: case KILLSTREAK:
                    userData.put(property, this.fileConfiguration.getInt(property.getStoragePath()));
                    break;
            }
        });

        return userData;
    }

    @Override
    public ParsedMap<GunGameUserProperty, Object> read(final GunGameUserProperty... properties) {
        if (properties == null) {
            return this.read();
        }
        final ParsedMap<GunGameUserProperty, Object> userData = new ParsedMap<>();

        for (final GunGameUserProperty property : properties) {
            switch (property) {
                case UNIQUE_ID:
                    userData.put(property, UUID.fromString(Objects.requireNonNull(this.fileConfiguration.getString(property.getStoragePath()))));
                    break;

                case NAME:
                    userData.put(property, this.fileConfiguration.getString(property.getStoragePath()));
                    break;

                case KILLS: case DEATHS: case KILLSTREAK:
                    userData.put(property, this.fileConfiguration.getInt(property.getStoragePath()));
                    break;

                default:
                    break;
            }
        }
        return userData;
    }

    @Override
    public void write(final ParsedMap<GunGameUserProperty, Object> userData) {
        userData.keySet().forEach(property -> {
            this.fileConfiguration.set(property.getStoragePath(), userData.get(property));
        });
        this.saveFile();
    }

    @Override
    @SneakyThrows
    public void saveFile() {
        this.fileConfiguration.save(this.configurationFile);
    }

}
