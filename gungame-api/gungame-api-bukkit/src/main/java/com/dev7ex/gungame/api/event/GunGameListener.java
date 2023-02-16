package com.dev7ex.gungame.api.event;

import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.GunGameApiConfiguration;
import com.dev7ex.gungame.api.user.GunGameUserProvider;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public abstract class GunGameListener implements Listener {

    private final GunGameApi gunGameApi;

    public GunGameListener(@NotNull final GunGameApi gunGameApi) {
        this.gunGameApi = gunGameApi;
    }

    public String getPrefix() {
        return this.gunGameApi.getConfiguration().getPrefix();
    }

    public GunGameApiConfiguration getApiConfiguration() {
        return this.gunGameApi.getConfiguration();
    }

    public GunGameUserProvider getUserProvider() {
        return this.gunGameApi.getUserProvider();
    }

}
