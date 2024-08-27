package de.revivemc.buildffa.storage.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    public ItemStack createItem(Material material, String displayName, List<String> lore, int subid, int amount) {
        ItemStack itemStack = new ItemStack(material, subid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack createItemWithEnchantments(Material material, String displayName, List<String> lore, Enchantment enchantment, int enchantmentlevel, boolean ignoreLevelRestriction, int subid, int amount) {
        ItemStack itemStack = new ItemStack(material, subid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, enchantmentlevel, ignoreLevelRestriction);
        itemMeta.setDisplayName(displayName);
        itemMeta.spigot().setUnbreakable(true);
        itemMeta.setLore(lore);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
