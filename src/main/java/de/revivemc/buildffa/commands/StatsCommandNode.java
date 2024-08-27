package de.revivemc.buildffa.commands;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.buildffa.storage.stats.StatsManager;
import de.revivemc.buildffa.storage.uuidfetcher.UUIDFetcher;
import de.revivemc.core.ReviveMCAPI;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StatsCommandNode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        ReviveMCPlayer cyturaPlayer = ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(player.getUniqueId());
        StatsManager statsManager = new StatsManager();
        int kills = statsManager.getKills(player.getUniqueId());
        int deaths = statsManager.getDeaths(player.getUniqueId());
        double kd = (double) kills / deaths;
        double finalkd = round(kd, 2);

        if(args.length > 1) {
            player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Verwende: 'stats' oder 'stats (name)'");
            return false;
        } else {
            if(args.length == 0) {
                player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + " ");
                player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Platzierung: §c#" + statsManager.getPlacement(player.getUniqueId()));
                player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Kills: §c" + kills);
                player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Tode: §c" + deaths);
                player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "K/D: §c" + finalkd);
                player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + " ");
            } else {
                UUID uuid = UUIDFetcher.getUUID(args[0]);
                int killso = statsManager.getKills(uuid);
                int deathso = statsManager.getDeaths(uuid);
                double kdo = (double) killso / deathso;
                double finalkdo = round(kdo, 2);
                try {
                    if(!statsManager.getPlacement(uuid).equals("-1")) {
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Statistiken über den Spieler §c" + args[0]);
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + " ");
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Platzierung: §c#" + statsManager.getPlacement(uuid));
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Kills: §c" + killso);
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Tode: §c" + deathso);
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "K/D: §c" + finalkdo);
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + " ");
                    } else {
                        player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Der Spieler §c" + args[0] + " §7hat noch keine Runde BuildFFA gespielt");
                    }
                }catch (Exception ex) {
                    player.sendMessage(BuildFFA.getInstance().getPrefix(cyturaPlayer) + "Der Spieler §c" + args[0] + " §7hat noch keine Runde BuildFFA gespielt");
                }
            }
        }
        return false;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
