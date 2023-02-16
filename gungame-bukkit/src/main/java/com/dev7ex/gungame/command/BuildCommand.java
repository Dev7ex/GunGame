package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import org.bukkit.command.CommandSender;


@CommandProperties(
        name = "build"
)

/**
 * @author DevSnx
 * @since 16.02.2023
 */
public class BuildCommand extends BukkitCommand {

    public BuildCommand(BukkitPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {




        return true;
    }
}