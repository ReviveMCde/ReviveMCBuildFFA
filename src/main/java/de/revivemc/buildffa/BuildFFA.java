package de.revivemc.buildffa;

import de.revivemc.buildffa.commands.SetSpawnCommandNode;
import de.revivemc.buildffa.commands.StatsCommandNode;
import de.revivemc.buildffa.listener.*;
import de.revivemc.buildffa.storage.sql.MySQL;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BuildFFA extends JavaPlugin {

    private static BuildFFA instance;
    public static MySQL mysql;

    @Override
    public void onEnable() {
        instance = this;
        mysql = new MySQL("localhost", "ReviveMC_Cloud", "root", "~aO_8QPm|5S!LNp{?PZt(+Ez%ldr$iY%6My[kjEaYy*D`(4A0FmM1ajku{z402]0");
        mysql.update("CREATE TABLE IF NOT EXISTS revivemc_buildffa_stats(UUID varchar(64), Kills Integer(100), Deaths Integer(100))");
        mysql.update("CREATE TABLE IF NOT EXISTS revivemc_buildffa_maps(Name varchar(24))");
        initListener();
        initCommands();
    }

    @Override
    public void onDisable() {
        mysql.close();
    }

    public void initListener() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerFallDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEntityDamageByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerFoodLevelChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerAsyncChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    public void initCommands() {
        getCommand("setspawn").setExecutor(new SetSpawnCommandNode());
        getCommand("stats").setExecutor(new StatsCommandNode());
    }

    public static BuildFFA getInstance() {
        return instance;
    }

    public String getPrefix(ReviveMCPlayer cyturaPlayer) {
        return "§8» " + cyturaPlayer.getFirstColor() + "BuildFFA §8× §7";
    }

    public static boolean playerExists(UUID uuid) {
        try {
            ResultSet rs = mysql.query("SELECT * FROM `revivemc_buildffa_stats` WHERE  `UUID` = '" + uuid.toString() + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void insertPlayer(UUID uuid) {
        if(!playerExists(uuid)) {
            mysql.update("INSERT INTO `revivemc_buildffa_stats`(`UUID`, `Kills`, `Deaths`) VALUES ('" + uuid + "', '0', '0');");
        }
    }
}
