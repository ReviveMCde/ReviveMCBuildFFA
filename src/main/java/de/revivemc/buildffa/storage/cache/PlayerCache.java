package de.revivemc.buildffa.storage.cache;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerCache {

    public static HashMap<Player, Integer> earnedCoins = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> doneKills = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> doneDeaths = new HashMap<Player, Integer>();

}
