package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.storage.cache.PlayerCache;
import de.revivemc.buildffa.storage.stats.StatsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        StatsManager statsManager = new StatsManager();
        /*
        Bei 0 Coins soll er keine abgezogen bekommen und wenn der abgezogene wert größer als die coins sind sollen die coins auf 0 gesetzt werden
         */
        /*if (statsManager.getCoins(player.getUniqueId()) >= PlayerCache.earnedCoins.get(player) || PlayerCache.earnedCoins.get(player) >= statsManager.getCoins(player.getUniqueId())) {
            statsManager.addCoins(player.getUniqueId(), PlayerCache.earnedCoins.get(player));
        } else {
            statsManager.setCoins(player.getUniqueId(), 0);
        } */
        //statsManager.addKills(player.getUniqueId(), PlayerCache.doneKills.get(player));
        //statsManager.addDeaths(player.getUniqueId(), PlayerCache.doneDeaths.get(player));
        PlayerMoveListener.isInFightingArea.remove(player);
        //PlayerCache.earnedCoins.remove(player);
        //PlayerCache.doneKills.remove(player);
        //PlayerCache.doneDeaths.remove(player);
    }
}
