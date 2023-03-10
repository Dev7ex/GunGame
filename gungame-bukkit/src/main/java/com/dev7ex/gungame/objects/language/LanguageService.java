package com.dev7ex.gungame.objects.language;

import com.dev7ex.gungame.GunGamePlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author DevSnx
 * @since 16.02.2023
 */
public class LanguageService {

    private String locale;
    private YamlConfiguration file;

    public LanguageService() { this.initialise(); }

    public void initialise() {

        this.locale = (String) GunGamePlugin.getInstance().getConfig().get("general.language");
        this.file = YamlConfiguration.loadConfiguration(new File(GunGamePlugin.getInstance().getDataFolder() + "/languages/" + this.getLocale() + ".yml"));

    }

    public String getLocale() {
        return this.locale;
    }

    public String getString(String path) {

        String message = this.file.getString(path);
        String prefix = String.valueOf(GunGamePlugin.getInstance().getConfiguration().getString("general.prefix"));
        message = message.replace("{prefix}", prefix);
        message = message.replace("&", "§");
        return message;

    }
}