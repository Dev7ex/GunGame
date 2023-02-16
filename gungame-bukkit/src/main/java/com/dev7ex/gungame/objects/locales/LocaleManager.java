package com.dev7ex.gungame.objects.locales;

import com.dev7ex.gungame.GunGamePlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LocaleManager {

    private String locale;
    private YamlConfiguration file;

    public LocaleManager() { this.initialise(); }

    public void initialise() {

        this.locale = (String) GunGamePlugin.getInstance().getConfig().get("general.locale");
        this.file = YamlConfiguration.loadConfiguration(new File(GunGamePlugin.getInstance().getDataFolder() + "/locales/" + this.getLocale() + ".yml"));

    }

    public String getLocale() {
        return this.locale;
    }

    public String getString(String path) {

        String message = this.file.getString(path);
        String prefix = String.valueOf(GunGamePlugin.getInstance().getConfig().getString("general.prefix"));
        message = message.replace("{prefix}", prefix);
        message = message.replace("&", "§");

        return message;

    }
}
