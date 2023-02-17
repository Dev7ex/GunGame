package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.gungame.GunGamePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


@CommandProperties(
        name = "build", permission = "gungame.command.build"
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

        if(!(commandSender instanceof Player)){
            return true;
        }

        if(strings.length == 0){
            if(GunGamePlugin.getInstance().getBuildingPlayers().contains((Player) commandSender)){

                commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-buildmode-off"));
                GunGamePlugin.getInstance().getBuildingPlayers().remove(((Player) commandSender).getPlayer());

            }else{

                commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-buildmode-on"));
                GunGamePlugin.getInstance().getBuildingPlayers().add(((Player) commandSender).getPlayer());
            }
        }else{

            commandSender.sendMessage(GunGamePlugin.getInstance().getLocaleManager().getString("command-buildmode-wrong"));
            return true;

        }

        return true;
    }

}