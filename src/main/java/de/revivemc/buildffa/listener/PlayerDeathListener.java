package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.buildffa.storage.cache.PlayerCache;
import de.revivemc.buildffa.storage.scoreboard.ScoreboardBuilder;
import de.revivemc.buildffa.storage.stats.StatsManager;
import de.revivemc.core.ReviveMCAPI;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();
        event.setDeathMessage(null);
        event.setDroppedExp(0);
        event.getDrops().clear();
        ReviveMCPlayer cyturaPlayer = ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(player.getUniqueId());
        ReviveMCPlayer cyturaKiller = ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(killer.getUniqueId());

        player.spigot().respawn();
        new ScoreboardBuilder().buildScoreboard(cyturaPlayer);
        new ScoreboardBuilder().buildScoreboard(cyturaKiller);

        StatsManager statsManager = new StatsManager();
        if (killer == null) {
            player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Du bist gestorben.");
            //PlayerCache.doneDeaths.put(player, PlayerCache.doneDeaths.get(player) + 1);
            statsManager.addDeaths(player.getUniqueId(), 1);
        } else if (killer == player) {
            player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Du bist gestorben.");
            //PlayerCache.doneDeaths.put(player, PlayerCache.doneDeaths.get(player) + 1);
            statsManager.addDeaths(player.getUniqueId(), 1);
        } else {
            double heart = round(killer.getHealth(), 1);
            player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Du wurdest von §c" + killer.getName() + " §7mit §c" + heart + " §7getötet.");
            player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "-5 Coins");
            //PlayerCache.doneDeaths.put(player, PlayerCache.doneDeaths.get(player) + 1);
            statsManager.addDeaths(player.getUniqueId(), 1);
            //PlayerCache.earnedCoins.put(player, PlayerCache.earnedCoins.get(player) - 5);
            killer.sendMessage(BuildFFA.getInstance().getPrefix(cyturaKiller) + "Du hast §c" + player.getName() + " §7mit §c" + heart + " §7getötet.");
            killer.sendMessage(BuildFFA.getInstance().getPrefix(cyturaKiller) + "+15 Coins");
            //PlayerCache.doneKills.put(killer, PlayerCache.doneKills.get(killer) + 1);
            statsManager.addKills(killer.getUniqueId(), 1);
            //PlayerCache.earnedCoins.put(killer, PlayerCache.earnedCoins.get(killer) + 15);
            //statsManager.addCoins(killer.getUniqueId(), 15);
            //statsManager.removeCoins(player.getUniqueId(), 5);
            killer.setHealth(20);
        }


    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
