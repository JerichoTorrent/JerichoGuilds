package com.jerichoguilds;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class GuildManager {
    private final Map<Player, String> playerGuilds = new HashMap<>();

    public void checkHonor(Player player, int honor) {
        String guild = getGuild(player);
        if (guild.equals("Bounty Hunters") && honor < 5) {
            leaveGuild(player);
            player.sendMessage("Your honor is too low to remain in the Bounty Hunters Guild!");
        } else if (guild.equals("Outlaws") && honor > -5) {
            leaveGuild(player);
            player.sendMessage("Your honor is too high to remain in the Outlaws Guild!");
        }
    }

    public void joinGuild(Player player, String guildName, int honor) {
        playerGuilds.put(player, guildName);
        player.sendMessage("You have joined the " + guildName + " guild.");
    }

    public void leaveGuild(Player player) {
        if (!isInGuild(player)) {
            player.sendMessage("You are not part of any guild.");
            return;
        }
        playerGuilds.remove(player);
        player.sendMessage("You have left your guild.");
    }

    public boolean isInGuild(Player player) {
        return playerGuilds.containsKey(player);
    }

    public String getGuild(Player player) {
        return playerGuilds.getOrDefault(player, "None");
    }
}
