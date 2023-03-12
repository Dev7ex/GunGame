package com.dev7ex.gungame;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.PluginProperties;
import com.dev7ex.gungame.api.GunGameApi;
import com.dev7ex.gungame.api.GunGameProvider;
import com.dev7ex.gungame.command.BuildCommand;
import com.dev7ex.gungame.command.SetSpawnCommand;
import com.dev7ex.gungame.command.StatsCommand;
import com.dev7ex.gungame.equipment.EquipmentConfiguration;
import com.dev7ex.gungame.equipment.EquipmentService;
import com.dev7ex.gungame.listener.*;
import com.dev7ex.gungame.location.LocationConfiguration;
import com.dev7ex.gungame.location.LocationService;
import com.dev7ex.gungame.objects.language.LanguageService;
import com.dev7ex.gungame.objects.scoreboard.ScoreboardService;
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
    private EquipmentConfiguration equipmentConfiguration;
    private LocationConfiguration locationConfiguration;
    private UserService userProvider;
    private EquipmentService equipmentService;
    private LocationService locationService;
    private LanguageService languageService;

    private ScoreboardService scoreboardService;

    @Override
    public void onLoad() {
        super.createDataFolder();
        super.createSubFolder("user");

        this.configuration = new GunGameConfiguration(this);
        this.configuration.load();

        this.equipmentConfiguration = new EquipmentConfiguration(this);
        this.equipmentConfiguration.createDefaults();

        this.locationConfiguration = new LocationConfiguration(this);
        this.locationConfiguration.createFile();

        // Load Languages  when not exists
        File file = new File(GunGamePlugin.getInstance().getDataFolder()+"/languages",  "en_US.yml");
        File file2 = new File(GunGamePlugin.getInstance().getDataFolder()+"/languages",  "de_DE.yml");

        if(!file.exists()){
            saveResource("languages/en_US.yml", false);
        }
        if(!file2.exists()){
            saveResource("languages/de_DE.yml", false);
        }

        languageService = new LanguageService();
        scoreboardService = new ScoreboardService();
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
        super.registerCommand(new BuildCommand(this));
        super.registerCommand(new StatsCommand(this));
        super.registerCommand(new SetSpawnCommand(this));
    }

    @Override
    public void registerListeners() {
        super.registerListener(new PlayerBuildListener(this));
        super.registerListener(new PlayerConnectionListener(this));
        super.registerListener(new PlayerDropItemListener(this));
        super.registerListener(new PlayerPickUpItemListener(this));
        super.registerListener(new PlayerMoveListener(this));
    }

    @Override
    public void registerServices() {
        super.registerService(this.userProvider = new UserService());
        super.registerService(this.equipmentService = new EquipmentService(this.equipmentConfiguration));
        super.registerService(this.locationService = new LocationService(this.locationConfiguration));
    }

    @Override
    public File getUserFolder() {
        return super.getSubFolder("user");
    }

    public static GunGamePlugin getInstance() {
        return JavaPlugin.getPlugin(GunGamePlugin.class);
    }

    public LanguageService getLanguageService() {
        return languageService;
    }

    public ScoreboardService getScoreboardService() {
        return scoreboardService;
    }
}
