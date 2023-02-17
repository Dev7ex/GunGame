package com.dev7ex.gungame.api;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public interface GunGameApiConfiguration {

    String getPrefix();

    String getString(@NotNull final String path);

    boolean getBoolean(@NotNull final String path);

}
