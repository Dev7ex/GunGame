package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.location.LocationService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 16.02.2023
 */
@CommandProperties(name = "setspawn", permission = "gungame.command.setspawn")
public class SetSpawnCommand extends BukkitCommand {

    public SetSpawnCommand(@NotNull final BukkitPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-execute-error"));
            return true;
        }

        if (arguments.length != 0) {
            commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-setspawn-wrong"));
            return true;
        }
        final LocationService locationService = GunGamePlugin.getInstance().getLocationService();
        locationService.cacheLocation("spawn", player.getLocation());
        locationService.getConfiguration().saveLocation("spawn", player.getLocation());
        commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-setspawn-sucsess"));
        return true;
    }

}
