package com.jerichoguilds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.UUID;

public class NotBountiesIntegration {

    private final Object notBountiesPlugin;
    private final Class<?> bountyManagerClass;

    public NotBountiesIntegration() throws ClassNotFoundException {
        this.notBountiesPlugin = Bukkit.getPluginManager().getPlugin("NotBounties");
        if (this.notBountiesPlugin == null) {
            throw new IllegalStateException("NotBounties plugin not found!");
        }
        this.bountyManagerClass = Class.forName("me.jadenp.notbounties.utils.BountyManager");
    }

    public boolean hasBounty(Player player) {
        try {
            Method hasBountyMethod = bountyManagerClass.getDeclaredMethod("hasBounty", UUID.class);
            return (boolean) hasBountyMethod.invoke(null, player.getUniqueId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setBounty(Player setter, Player target, double amount) {
        try {
            Method setBountyMethod = bountyManagerClass.getDeclaredMethod("addBounty", Player.class, Player.class, double.class);
            setBountyMethod.invoke(null, setter, target, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
