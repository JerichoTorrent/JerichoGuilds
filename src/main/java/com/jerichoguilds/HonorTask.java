package com.jerichoguilds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HonorTask extends BukkitRunnable {

    private final GuildManager guildManager;
    private final HonorIntegration honorIntegration;

    public HonorTask(GuildManager guildManager, HonorIntegration honorIntegration) {
        this.guildManager = guildManager;
        this.honorIntegration = honorIntegration;
    }

    @Override
    public void run() {
        if (honorIntegration == null) {
            Bukkit.getLogger().warning("[JerichoGuilds] HonorIntegration is not initialized. Skipping honor check task.");
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            int honor = honorIntegration.getHonor(player);
            guildManager.checkHonor(player, honor);
        }
    }
}
