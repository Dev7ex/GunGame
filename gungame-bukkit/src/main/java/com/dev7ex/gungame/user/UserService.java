package com.dev7ex.gungame.user;

import com.dev7ex.common.bukkit.plugin.service.PluginService;
import com.dev7ex.common.map.ParsedMap;
import com.dev7ex.gungame.api.user.GunGameUser;
import com.dev7ex.gungame.api.user.GunGameUserConfiguration;
import com.dev7ex.gungame.api.user.GunGameUserProperty;
import com.dev7ex.gungame.api.user.GunGameUserProvider;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
public class UserService implements PluginService, GunGameUserProvider {

    private final Map<UUID, GunGameUser> users = new HashMap<>();

    @Override
    public void onEnable() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final GunGameUser user = new User(player.getUniqueId(), player.getName());
            final GunGameUserConfiguration userConfiguration = new UserConfiguration(user);

            this.registerUser(user);
        }
    }

    @Override
    public void onDisable() {
        this.users.clear();
    }

    @Override
    public void registerUser(@NotNull final GunGameUser user) {
        this.users.put(user.getUniqueId(), user);
    }

    @Override
    public void unregisterUser(@NotNull final UUID uniqueId) {
        this.users.remove(uniqueId);
    }

    @Override
    public Optional<GunGameUser> getUser(@NotNull final UUID uniqueId) {
        return Optional.ofNullable(this.users.get(uniqueId));
    }

    @Override
    public Optional<GunGameUser> getUser(@NotNull final String name) {
        return this.users.values().stream().filter(gunGameUser -> gunGameUser.getName().equalsIgnoreCase(name)).findFirst();
    }


    @Override
    public void saveUser(@NotNull final GunGameUser user) {
        this.saveUser(user, GunGameUserProperty.UNIQUE_ID,
                GunGameUserProperty.NAME);
    }

    @Override
    public void saveUser(@NotNull final GunGameUser user, @NotNull final GunGameUserProperty... properties) {
        final ParsedMap<GunGameUserProperty, Object> data = new ParsedMap<>();

        for (final GunGameUserProperty property : properties) {
            switch (property) {
                case UNIQUE_ID:
                    data.put(property, user.getUniqueId());
                    break;

                case NAME:
                    data.put(property, user.getName());
                    break;

                default:
                    this.saveUser(user);
                    break;
            }
        }
        user.getConfiguration().write(data);
    }

}
