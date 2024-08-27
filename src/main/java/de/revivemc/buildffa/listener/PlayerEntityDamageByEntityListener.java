package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.storage.location.LocationBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerEntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onPlayerEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Player player = (Player)event.getEntity();
        if(player.getLocation().getY() >= new LocationBuilder().getMapItemsLocation("CWBW", player).getY()) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
}
