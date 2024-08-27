package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.core.ReviveMCAPI;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) {
            return;
        }
        if (event.getClickedBlock().getType() == null) {
            return;
        }

        if (event.getClickedBlock().getType().equals(Material.CHEST) || event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
            event.setCancelled(true);
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ReviveMCPlayer cyturaPlayer = ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(player.getUniqueId());
            if (player.getItemInHand() == null) {
                return;
            }
            if (player.getItemInHand().getItemMeta() == null) {
                return;
            }
            if (player.getItemInHand().getItemMeta().getDisplayName() == null) {
                return;
            }

            if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8§l» §cSpiel Verlassen §8§l«")) {
                player.kickPlayer(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Du hast das Spiel verlassen.");
            }
        }
    }
}
