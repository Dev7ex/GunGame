package com.dev7ex.gungame.api;

import com.dev7ex.gungame.api.user.GunGameUserProvider;

import java.io.File;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public interface GunGameApi {

    GunGameApiConfiguration getConfiguration();

    GunGameUserProvider getUserProvider();

    File getUserFolder();

}
