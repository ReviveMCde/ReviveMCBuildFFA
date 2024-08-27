package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.buildffa.storage.cache.PlayerCache;
import de.revivemc.buildffa.storage.items.ItemBuilder;
import de.revivemc.buildffa.storage.location.LocationBuilder;
import de.revivemc.buildffa.storage.scoreboard.ScoreboardBuilder;
import de.revivemc.core.ReviveMCAPI;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import de.revivemc.core.playerutils.events.ReviveMCPlayerJoinEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(ReviveMCPlayerJoinEvent event) {
        Player player = event.getPlayer();
        ReviveMCPlayer cyturaPlayer = ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(player.getUniqueId());
        cyturaPlayer.setTablist();

        new ScoreboardBuilder().buildScoreboard(cyturaPlayer);

        BuildFFA.insertPlayer(player.getUniqueId());
        giveItems(player);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        if(!player.hasPlayedBefore()) {
            player.teleport(new LocationBuilder().getMapPlayerLocation("CWBW", player));
        }
        player.teleport(new LocationBuilder().getMapPlayerLocation("CWBW", player));
        //PlayerCache.doneDeaths.put(player, 0);
        //PlayerCache.doneKills.put(player, 0);
        //PlayerCache.earnedCoins.put(player, 0);
    }

    public void giveItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(4, new ItemBuilder().createItem(Material.CHEST, "§8§l» §cInventarsortierung §8§l«", Arrays.asList("§7Passe deine Inventarsortiertung,", "§7auf deinen Geschmack an"), 0, 1));
        player.getInventory().setItem(8, new ItemBuilder().createItem(Material.SLIME_BALL, "§8§l» §cSpiel Verlassen §8§l«", null, 0, 1));
    }

}
