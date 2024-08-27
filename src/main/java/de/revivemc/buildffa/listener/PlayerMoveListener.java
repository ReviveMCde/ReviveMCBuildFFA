package de.revivemc.buildffa.listener;

import de.revivemc.buildffa.storage.items.ItemBuilder;
import de.revivemc.buildffa.storage.location.LocationBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class PlayerMoveListener implements Listener {

    public static ArrayList<Player> isInFightingArea = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() <= new LocationBuilder().getMapItemsLocation("CWBW", player).getY()) {
            if(!isInFightingArea.contains(player)) {
                giveFightItems(player);
                isInFightingArea.add(player);
                player.closeInventory();
            }
        }

        if (player.getLocation().getY() <= new LocationBuilder().getMapDeathLocation("CWBW", player).getY()) {
            player.damage(100.0);
            player.spigot().respawn();
        }
    }

    public void giveFightItems(Player player) {
        player.getInventory().clear();
        ItemBuilder itemBuilder = new ItemBuilder();
        player.getInventory().setItem(0, itemBuilder.createItemWithEnchantments(Material.GOLD_SWORD, "§6Gold Schwert", null, Enchantment.DAMAGE_ALL, 1, true, 0, 1));
        player.getInventory().setItem(1, itemBuilder.createItem(Material.SANDSTONE, "§7Sandstein", null, 0, 64));
        player.getInventory().setItem(2, itemBuilder.createItemWithEnchantments(Material.STICK, "§6Knockback Stick", null, Enchantment.KNOCKBACK, 1, true, 0, 1));
        player.getInventory().setItem(3, itemBuilder.createItem(Material.LADDER, "§7Leiter", null, 0, 4));
        player.getInventory().setItem(4, itemBuilder.createItem(Material.SANDSTONE, "§7Sandstein", null, 0, 64));
        player.getInventory().setItem(5, itemBuilder.createItem(Material.SANDSTONE, "§7Sandstein", null, 0, 64));
        player.getInventory().setItem(7, itemBuilder.createItem(Material.WEB, "§7Spinnennetz", null, 0, 2));
        player.getInventory().setItem(8, itemBuilder.createItem(Material.ENDER_PEARL, "§7Enderperle", null, 0, 2));
        player.getInventory().setHelmet(itemBuilder.createItemWithEnchantments(Material.LEATHER_HELMET, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, true,0, 1));
        player.getInventory().setLeggings(itemBuilder.createItemWithEnchantments(Material.LEATHER_LEGGINGS, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, true,0, 1));
        player.getInventory().setBoots(itemBuilder.createItemWithEnchantments(Material.LEATHER_BOOTS, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, true,0, 1));
        player.getInventory().setChestplate(itemBuilder.createItemWithEnchantments(Material.IRON_CHESTPLATE, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, true,0, 1));
    }
}
