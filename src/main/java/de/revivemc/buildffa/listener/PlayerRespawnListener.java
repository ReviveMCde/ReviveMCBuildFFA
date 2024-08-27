package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.buildffa.storage.location.LocationBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.setVelocity(new Vector(0, 0, 0));
        Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), () -> {
            player.teleport(new LocationBuilder().getMapPlayerLocation("CWBW", player));
            new PlayerJoinListener().giveItems(player);
            PlayerMoveListener.isInFightingArea.remove(player);
        }, 4L);
    }
}
