package de.revivemc.buildffa.storage.stats;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.core.ReviveMCAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StatsManager {

    public Integer getKills(UUID uuid) {
        int integer = 0;
        try {
            ResultSet resultSet = BuildFFA.mysql.query("SELECT * FROM revivemc_buildffa_stats WHERE UUID='" + uuid + "'");
            if(resultSet.next()) {
                integer = resultSet.getInt("Kills");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return integer;
    }

    public Integer getCoins(UUID uuid) {
        return ReviveMCAPI.getInstance().getCoinsUtils().getCoins(ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(uuid));
    }

    public Integer getDeaths(UUID uuid) {
        int integer = 0;
        try {
            ResultSet resultSet = BuildFFA.mysql.query("SELECT * FROM revivemc_buildffa_stats WHERE UUID='" + uuid + "'");
            if(resultSet.next()) {
                integer = resultSet.getInt("Deaths");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return integer;
    }

    public void setKills(UUID uuid, int kills) {
        BuildFFA.mysql.update("UPDATE revivemc_buildffa_stats SET Kills='" + kills + "' WHERE UUID='" + uuid + "'");
    }

    public void addKills(UUID uuid, int kills) {
        setKills(uuid, getKills(uuid) + kills);
    }

    public void setCoins(UUID uuid, int coins) {
        ReviveMCAPI.getInstance().getCoinsUtils().setCoins(ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(uuid), coins);
    }

    public void addCoins(UUID uuid, int coins) {
        setCoins(uuid, getCoins(uuid) + coins);
    }

    public void removeCoins(UUID uuid, int coins) {
        setCoins(uuid, getCoins(uuid) - coins);
    }

    public void setDeaths(UUID uuid, int deaths) {
        BuildFFA.mysql.update("UPDATE revivemc_buildffa_stats SET Deaths='" + deaths + "' WHERE UUID='" + uuid + "'");
    }

    public void addDeaths(UUID uuid, int deaths) {
        setDeaths(uuid, getKills(uuid) + deaths);
    }

    public Integer getPlacement(UUID uuid) {
        int integer = -1;
        try {
            ResultSet resultSet = BuildFFA.mysql.query("SELECT * FROM revivemc_buildffa_stats ORDER BY Kills DESC");
            while (resultSet.next()) {
                String uuid2 = resultSet.getString("UUID");
                if(uuid2.equalsIgnoreCase(uuid.toString())) {
                    integer = resultSet.getRow();
                    break;
                }
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return integer;
    }

}
