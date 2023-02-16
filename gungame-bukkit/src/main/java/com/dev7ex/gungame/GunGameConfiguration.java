package com.dev7ex.gungame;

import com.dev7ex.common.bukkit.configuration.ConfigurationProperties;
import com.dev7ex.common.bukkit.plugin.configuration.DefaultPluginConfiguration;
import com.dev7ex.gungame.api.GunGameApiConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@ConfigurationProperties(fileName = "config.yml")
public class GunGameConfiguration extends DefaultPluginConfiguration implements GunGameApiConfiguration {

    public GunGameConfiguration(@NotNull final Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getPrefix() {
        return super.getPrefix();
    }

}
