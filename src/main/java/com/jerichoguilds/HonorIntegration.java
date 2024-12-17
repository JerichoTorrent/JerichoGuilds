package com.jerichoguilds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;

public class HonorIntegration {
    private Object honorPlugin;
    private Object honorDatabase;

    public void initialize() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("HonorPlugin");
        if (plugin != null) {
            honorPlugin = plugin;
            Bukkit.getLogger().info("[JerichoGuilds] HonorPlugin successfully detected.");
            try {
                honorDatabase = honorPlugin.getClass().getMethod("getHonorDatabase").invoke(honorPlugin);
                Bukkit.getLogger().info("[JerichoGuilds] HonorDatabase retrieved successfully. Listing methods:");
                
                for (Method method : honorDatabase.getClass().getMethods()) {
                    Bukkit.getLogger().info(method.toString());
                }
            } catch (Exception e) {
                Bukkit.getLogger().severe("[JerichoGuilds] Failed to retrieve HonorDatabase: " + e.getMessage());
            }
        } else {
            Bukkit.getLogger().warning("[JerichoGuilds] HonorPlugin not found. Honor features will not work.");
        }
    }

    public int getHonor(Player player) {
        if (honorDatabase == null) {
            Bukkit.getLogger().warning("[JerichoGuilds] HonorDatabase is not initialized.");
            return 0;
        }
        // Fucking shit let's try this
        try {
            Bukkit.getLogger().info("[JerichoGuilds] Attempting to call getHonor method for player " + player.getName());
            return (int) honorDatabase.getClass()
                    .getMethod("getHonor", Player.class)
                    .invoke(honorDatabase, player);
        } catch (NoSuchMethodException e) {
            Bukkit.getLogger().severe("[JerichoGuilds] HonorDatabase does not have the expected getHonor method.");
        } catch (Exception e) {
            Bukkit.getLogger().severe("[JerichoGuilds] Failed to retrieve honor for player " + player.getName() + ": " + e.getMessage());
        }
        return 0;
    }
}
