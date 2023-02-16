package com.dev7ex.gungame.api.user;

import com.dev7ex.common.map.ParsedMap;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public interface GunGameUserConfiguration {

    ParsedMap<GunGameUserProperty, Object> read();

    ParsedMap<GunGameUserProperty, Object> read(@NotNull final GunGameUserProperty... properties);

    void write(@NotNull final ParsedMap<GunGameUserProperty, Object> userData);

    void saveFile();

}
