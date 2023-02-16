package com.dev7ex.gungame;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.PluginProperties;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.GunGameProvider;
import com.dev7ex.gungame.listener.PlayerConnectionListener;
import com.dev7ex.gungame.user.UserService;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@Getter(AccessLevel.PUBLIC)
@PluginProperties
public class GunGamePlugin extends BukkitPlugin implements GunGameApi {

    private GunGameConfiguration configuration;

    private UserService userProvider;

    @Override
    public void onLoad() {
        super.createDataFolder();
        super.createSubFolder("user");

        this.configuration = new GunGameConfiguration(this);
        this.configuration.load();
    }

    @Override
    public void onEnable() {
        GunGameProvider.registerApi(this);
    }

    @Override
    public void onDisable() {
        GunGameProvider.unregisterApi();
    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void registerListeners() {
        super.registerListener(new PlayerConnectionListener(this));
    }

    @Override
    public void registerServices() {
        super.registerService(this.userProvider = new UserService());
    }

    @Override
    public File getUserFolder() {
        return super.getSubFolder("user");
    }

    public static GunGamePlugin getInstance() {
        return JavaPlugin.getPlugin(GunGamePlugin.class);
    }

}
