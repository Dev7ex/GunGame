package com.dev7ex.gungame.objects.scoreboard;

import com.dev7ex.gungame.GunGamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;


/**
 * @author DevSnx
 * @since 10.03.2023
 */
public class ScoreboardService {

    private Map<Player, Scoreboard> playerScoreboards;

    public ScoreboardService() {
        this.playerScoreboards = new HashMap<>();
    }

    public void createNewScoreboard(Player p) {
        if (this.playerScoreboards.containsKey(p)) {
            return;
        }
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective sidebar = board.registerNewObjective("Sidebar", "dummy");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        sidebar.setDisplayName("§l§f─═ §bGunGame §f§l═─");

        //SCORES GETTEN AUS CONFIG

        p.setScoreboard(board);
        this.playerScoreboards.put(p, board);

        updateSidebar(p);
    }

    public void updateSidebar(Player forWhom){
        if (!this.playerScoreboards.containsKey(forWhom))
            createNewScoreboard(forWhom);
        Scoreboard board = this.playerScoreboards.get(forWhom);

        //SCORES UPDATEN AUS CONFIG

    }

    public void removePlayerScoreboard(Player p) {
        if (!this.playerScoreboards.containsKey(p))
            return;
        Scoreboard board = this.playerScoreboards.get(p);
        for (Objective scoreOb : board.getObjectives())
            scoreOb.unregister();
        for (Team scoreTeam : board.getTeams())
            scoreTeam.unregister();
        this.playerScoreboards.remove(p);
        p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void updateScoreboards() {
        for (Player all : this.playerScoreboards.keySet())
            updateSidebar(all);
    }

    private void startSidebarUpdater() {
        (new BukkitRunnable() {
            public void run() {
                updateScoreboards();
            }
        }).runTaskTimer((GunGamePlugin) GunGamePlugin.getInstance(), 60L, 60L);
    }
}