package de.revivemc.buildffa.commands;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.buildffa.storage.location.LocationBuilder;
import de.revivemc.core.ReviveMCAPI;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommandNode implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        final ReviveMCPlayer reviveMCPlayer = ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(player.getUniqueId());
        if (!player.hasPermission("buildffa.setspawn")) {
            player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "§cDu hast keine Rechte dafür diesen Befehl ausführen zu können!");
            return false;
        }

        if (args.length != 2) {
            player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "Verwende: 'setspawn (mapname) (id)'");
            return false;
        }

        String mapName = args[0];
        int id = Integer.parseInt(args[1]);

        try {
            switch (id) {
                case 1:
                    new LocationBuilder().createMapPlayerLocation(mapName, player.getLocation());
                    BuildFFA.mysql.update("INSERT INTO `cyturanet_buildffa_maps`(`Name`) VALUES ('" + mapName + "')");
                    player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "Du hast den SpawnPunkt gesetzt.");
                    break;

                case 2:
                    new LocationBuilder().createMapItemsLocation(mapName, player.getLocation());
                    player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "Du hast den GiveItems-Location gesetzt.");
                    break;

                case 3:
                    new LocationBuilder().createMapDeathLocation(mapName, player.getLocation());
                    player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "Du hast die Todeshöhe gesetzt.");
                    break;

                default:
                    player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "(1) SpawnPunkt");
                    player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "(2) GiveItems");
                    player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "(3) Todeshöhe");
            }
        } catch (NumberFormatException ex) {
            player.sendMessage(BuildFFA.getInstance().getPrefix(reviveMCPlayer) + "Du musst eine gültige id eingeben.");
        }
        return false;
    }
}
