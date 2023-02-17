package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.gungame.GunGamePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dev7ex.gungame.api.user.GunGameUser;
import org.jetbrains.annotations.NotNull;

@CommandProperties(name = "build", permission = "gungame.command.build")

/**
 * @author DevSnx
 * @since 16.02.2023
 */
public class BuildCommand extends BukkitCommand {

    public BuildCommand(BukkitPlugin plugin) {
        super(plugin);
    }

    public boolean execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-execute-error"));
            return true;
        }

        if (arguments.length != 0) {
            commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-buildmode-wrong"));
            return true;
        }
        final GunGameUser user = GunGamePlugin.getInstance().getUserProvider().getUser(player.getUniqueId()).orElseThrow();

        if (!user.isBuildMode()) {
            commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-buildmode-on"));
            user.setBuildMode(true);
            return true;
        }
        user.setBuildMode(false);
        commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-buildmode-off"));
        return true;
    }

}