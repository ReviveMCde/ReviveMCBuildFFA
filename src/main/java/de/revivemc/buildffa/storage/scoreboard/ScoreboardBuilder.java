package de.revivemc.buildffa.storage.scoreboard;

import de.revivemc.buildffa.BuildFFA;
import de.revivemc.buildffa.storage.stats.StatsManager;
import de.revivemc.core.ReviveMCAPI;
import de.revivemc.core.playerutils.ReviveMCPlayer;
import de.revivemc.core.playerutils.scoreboard.ReviveMCScoreboardBuilder;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardBuilder {

    public void buildScoreboard(ReviveMCPlayer cyturaPlayer) {
        cyturaPlayer.initialScoreboard();
        final ReviveMCScoreboardBuilder cyturaScoreboardBuilder = cyturaPlayer.getCyturaScoreboardBuilder();
        cyturaScoreboardBuilder.setLine(10, "§8§m---------", "§8§m---------");
        cyturaScoreboardBuilder.setLine(9, " ", " ");
        cyturaScoreboardBuilder.setLine(8, " §8§l» ", "§7Map");
        cyturaScoreboardBuilder.setLine(7, " §8» ", cyturaPlayer.getSecondColor() + "CWBW");
        cyturaScoreboardBuilder.setLine(6, " ", " ");
        cyturaScoreboardBuilder.setLine(5, " §8§l» ", "§7Kills");
        cyturaScoreboardBuilder.setLine(4, " §8» ", cyturaPlayer.getSecondColor() + "§l" + new StatsManager().getKills(cyturaPlayer.getUuid()));
        cyturaScoreboardBuilder.setLine(3, " ", " ");
        cyturaScoreboardBuilder.setLine(2, " §8§l» ", "§7Teamspeak");
        cyturaScoreboardBuilder.setLine(1, " §8» ", cyturaPlayer.getSecondColor() + "ReviveMC.de");
        cyturaScoreboardBuilder.setLine(0, " ", " ");
        MinecraftServer.getServer().postToMainThread(() -> {
            cyturaScoreboardBuilder.setBoard(cyturaPlayer.getFirstColor() + "§lReviveMC §8× §7BuildFFA");
            Bukkit.getOnlinePlayers().forEach(player -> {
                ReviveMCAPI.getInstance().getCyturaTablistManager().setDefaultTablist(ReviveMCAPI.getInstance().getCyturaPlayerManager().getPlayers().get(player.getUniqueId()));
            });
        });
    }
}