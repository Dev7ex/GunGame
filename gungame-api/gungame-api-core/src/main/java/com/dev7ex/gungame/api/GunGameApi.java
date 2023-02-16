package com.dev7ex.gungame.api;

import com.dev7ex.gungame.api.user.GunGameUserProvider;

import java.io.File;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
public interface GunGameApi {

    GunGameUserProvider getUserProvider();

    File getUserFolder();

}
