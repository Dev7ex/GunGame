package com.dev7ex.gungame.api.user;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
public enum GunGameUserProperty {

    UNIQUE_ID("unique-id"),
    NAME("name"),
    KILLS("kills"),
    DEATHS("deaths"),
    KILLSTREAK("killstreak");

    private final String storagePath;

    GunGameUserProperty(final String storagePath) {
        this.storagePath = storagePath;
    }

}