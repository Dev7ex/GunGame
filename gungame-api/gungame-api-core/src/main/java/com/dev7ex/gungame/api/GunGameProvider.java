package com.dev7ex.gungame.api;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public class GunGameProvider {

    @Getter(AccessLevel.PUBLIC)
    private static GunGameApi gunGameApi;

    public static void registerApi(@NotNull final GunGameApi gunGameApi) {
        GunGameProvider.gunGameApi = gunGameApi;
    }

    public static void unregisterApi() {
        GunGameProvider.gunGameApi = null;
    }

}
