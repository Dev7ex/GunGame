package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.gungame.GunGamePlugin;
<<<<<<< HEAD
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


@CommandProperties(
        name = "build", permission = "gungame.command.build"
)

=======
import com.dev7ex.gungame.api.user.GunGameUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


@CommandProperties(name = "build", permission = "gungame.command.build")
>>>>>>> 65d0ab6ea871ec8818e5fd354897bca600bafc16
/**
 * @author DevSnx
 * @since 16.02.2023
 */
public class BuildCommand extends BukkitCommand {

    public BuildCommand(BukkitPlugin plugin) {
        super(plugin);
    }

    @Override
<<<<<<< HEAD
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
=======
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
>>>>>>> 65d0ab6ea871ec8818e5fd354897bca600bafc16

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