package com.dev7ex.gungame.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.gungame.GunGamePlugin;
import com.dev7ex.gungame.api.user.GunGameUser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.print.DocFlavor;

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
            sendStats(player, player.getName());
            return true;
        }

        if(arguments.length == 1){
            sendStats(player, arguments[0]);
            return true;
        }

        return true;
    }

    private void sendStats(Player fromWho, String target){

        if(!(GunGamePlugin.getInstance().getUserProvider().getUser(target).isEmpty())){

            fromWho.sendMessage(GunGamePlugin.getInstance().getLanguageService().getString("player-has-no-stats"));

            return;
        }

        final GunGameUser user = GunGamePlugin.getInstance().getUserProvider().getUser(Bukkit.getPlayer(target).getUniqueId()).orElseThrow();

        for(String message : GunGamePlugin.getInstance().getConfiguration().getStringList("stats.")){

            message = message.replace("{kills}", String.valueOf(user.getKills()));
            message = message.replace("{deaths}", String.valueOf(user.getDeaths()));
            // KD = message = message.replace("{kd}", String.valueOf(user.getKD()));
            message = message.replace("{killstreak}", String.valueOf(user.getKillStreak()));
            message = message.replace("{level}", String.valueOf(user.getLevel()));
            message = message.replace("{0}", target);
            message = message.replace("&", "§");

            fromWho.sendMessage(message);

        }
    }
}