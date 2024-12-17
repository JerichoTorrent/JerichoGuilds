package com.jerichoguilds;

import org.bukkit.plugin.java.JavaPlugin;

public class JerichoGuilds extends JavaPlugin {

    private GuildManager guildManager;
    private HonorIntegration honorIntegration;

    @Override
    public void onEnable() {
        honorIntegration = new HonorIntegration();
        honorIntegration.initialize();

        guildManager = new GuildManager();

        GuildCommands guildCommands = new GuildCommands(guildManager, honorIntegration);
        getCommand("guild").setExecutor(guildCommands);
        getCommand("guild").setTabCompleter(new GuildTabCompleter());

        getLogger().info("[JerichoGuilds] Plugin enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("[JerichoGuilds] Plugin disabled successfully!");
    }
}
