package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.buildffa.storage.location.LocationBuilder;
import de.revivemc.core.ReviveMCAPI;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockPlaceListener implements Listener {

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ReviveMCPlayer cyturaPlayer = ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(player.getUniqueId());

        if(event.getBlock().getLocation().getY() >= new LocationBuilder().getMapItemsLocation("CWBW", player).getY()) {
            event.setCancelled(true);
        } else if(event.getBlock().getLocation().getY() >= new LocationBuilder().getMapItemsLocation("CWBW", player).getY() - 3.0D) {
            event.setCancelled(true);
            player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Du kannst hier nicht bauen!");
        }

        if(!event.isCancelled()) {
            if (event.getBlock().getType().equals(Material.SANDSTONE)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), () -> {
                    event.getBlock().setType(Material.RED_SANDSTONE);
                }, 40L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), () -> {
                    event.getBlock().setType(Material.AIR);
                }, 60L);
            } else if (event.getBlock().getType().equals(Material.WEB)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), () -> {
                    event.getBlock().setType(Material.AIR);
                }, 100L);
            } else if (event.getBlock().getType().equals(Material.LADDER)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), () -> {
                    event.getBlock().setType(Material.AIR);
                }, 100L);
            }
        }
    }
}
