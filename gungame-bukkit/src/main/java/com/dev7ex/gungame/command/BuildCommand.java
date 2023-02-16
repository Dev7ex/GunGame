package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.user.GunGameUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

    @Override
    public boolean execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(super.getConfiguration().getString(""));
            return true;
        }

        if (arguments.length != 0) {
            commandSender.sendMessage(super.getConfiguration().getString("")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }
        final GunGameUser user = GunGamePlugin.getInstance().getUserProvider().getUser(player.getUniqueId()).orElseThrow();

        if (!user.isBuildMode()) {
            commandSender.sendMessage(super.getConfiguration().getString(""));
            user.setBuildMode(true);
            return true;
        }
        user.setBuildMode(false);
        commandSender.sendMessage(super.getConfiguration().getString(""));
        return true;
    }
}