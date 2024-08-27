package de.revivemc.buildffa.storage.location;

import de.revivemc.buildffa.BuildFFA;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationBuilder {

    private final File file = new File("plugins//BuildFFA//locations.yml");
    private final YamlConfiguration configuration;

    public LocationBuilder() {
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void createMapPlayerLocation(String mapName, Location location) {
        try {
            configuration.set(mapName + ".X", location.getX());
            configuration.set(mapName + ".Y", location.getY());
            configuration.set(mapName + ".Z", location.getZ());
            configuration.set(mapName + ".Yaw", location.getYaw());
            configuration.set(mapName + ".Pitch", location.getPitch());
            configuration.set(mapName + ".Word", location.getWorld().getName());
            configuration.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createMapItemsLocation(String mapName, Location location) {
        try {
            configuration.set(mapName + ".GiveItems", location.getY());
            configuration.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createMapDeathLocation(String mapName, Location location) {
        try {
            configuration.set(mapName + ".DeathHeight", location.getY());
            configuration.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Location getMapPlayerLocation(String mapName, Player player) {
        double X = configuration.getDouble(mapName + ".X");
        double Y = configuration.getDouble(mapName + ".Y");
        double Z = configuration.getDouble(mapName + ".Z");
        double Yaw = configuration.getDouble(mapName + ".Yaw");
        double Pitch = configuration.getDouble(mapName + ".Pitch");
        World world = BuildFFA.getInstance().getServer().getWorld(configuration.getString(mapName + ".Word"));
        Location location = new Location(world, X, Y, Z);
        location.setYaw((float)Yaw);
        location.setPitch((float)Pitch);
        return location;
    }

    public Location getMapItemsLocation(String mapName, Player player) {
        Location location = player.getLocation();
        location.setY(configuration.getDouble(mapName + ".GiveItems"));
        return location;
    }

    public Location getMapDeathLocation(String mapName, Player player) {
        Location location = player.getLocation();
        location.setY(configuration.getDouble(mapName + ".DeathHeight"));
        return location;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    public File getFile() {
        return file;
    }
}
