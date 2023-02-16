package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import org.bukkit.command.CommandSender;


@CommandProperties(
        name = "build"
)

public class CommandBuild extends BukkitCommand {

    public CommandBuild(BukkitPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return true;
    }
}