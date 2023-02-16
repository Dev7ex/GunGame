package com.dev7ex.gungame.api.user;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public interface GunGameUserProvider {

    void registerUser(@NotNull final GunGameUser user);

    void unregisterUser(@NotNull final UUID uniqueId);

    Optional<GunGameUser> getUser(@NotNull final UUID uniqueId);

    Optional<GunGameUser> getUser(@NotNull final String name);

    Map<UUID, GunGameUser> getUsers();

    void saveUser(@NotNull final GunGameUser user);

    void saveUser(@NotNull final GunGameUser user, @NotNull final GunGameUserProperty... properties);

}
