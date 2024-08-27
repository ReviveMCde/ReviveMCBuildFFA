package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.storage.location.LocationBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if(event.getTo().getY() >= new LocationBuilder().getMapItemsLocation("CWBW", event.getPlayer()).getY()) {
            if(event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
                event.setCancelled(true);
            }
        }

        if(event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            if(event.getPlayer().getLocation().equals(event.getTo())) {
                event.getPlayer().damage(0);
            }
        }

        /*if(event.getPlayer().) {

        } */
    }
}
