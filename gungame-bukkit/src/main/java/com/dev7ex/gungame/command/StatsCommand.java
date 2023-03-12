package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.user.GunGameUser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.print.DocFlavor;
import java.util.UUID;

@CommandProperties(name = "stats", permission = "gungame.command.stats")
public class StatsCommand extends BukkitCommand {

    public StatsCommand(@NotNull final BukkitPlugin plugin) {
        super(plugin);
    }

    public boolean execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("command-execute-error"));
            return true;
        }

        if(!(arguments.length <= 1)){
            commandSender.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("command-stats-wrong"));
            return true;
        }

        if(arguments.length == 0){
            sendStats(player, player);
            return true;
        }

        if(arguments.length == 1){
            Player targetOnline = Bukkit.getPlayer(arguments[0]);
            if (targetOnline != null) {
                sendStats(player, targetOnline);
            } else {

                UUID uuid = Bukkit.getOfflinePlayer(arguments[0]).getUniqueId();

                if (uuid == null) {

                    player.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("target-has-no-stats").replace("{0}", Bukkit.getOfflinePlayer(uuid).getName()));
                    return true;

                } else {

                    sendStats(player, (Player) Bukkit.getOfflinePlayer(uuid));

                }

            }

            return true;
        }

        return true;
    }

    private void sendStats(Player fromWho, Player whoFrom){

        if(!(GunGamePlugin.getInstance().getUserProvider().getUser(whoFrom.getName()).isEmpty())){

            if(fromWho == whoFrom){
                fromWho.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("target-has-no-stats").replace("{0}", whoFrom.getName()));
                return;
            }

            fromWho.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("player-has-no-stats"));

            return;
        }

        final GunGameUser user = GunGamePlugin.getInstance().getUserProvider().getUser(whoFrom.getUniqueId()).orElseThrow();

        fromWho.sendMessage("1");

        for(String message : GunGamePlugin.getInstance().getConfiguration().getStringList("stats.messages")){

            fromWho.sendMessage(message);

            message = message.replace("{kills}", String.valueOf(user.getKills()));
            message = message.replace("{deaths}", String.valueOf(user.getDeaths()));
            message = message.replace("{killstreak}", String.valueOf(user.getKillStreak()));
            message = message.replace("{level}", String.valueOf(user.getLevel()));
            message = message.replace("{0}", whoFrom.getName());
            message = message.replace("&", "§");

            fromWho.sendMessage(message);

        }
    }
}