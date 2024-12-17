package com.jerichoguilds;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Arrays;

public class GuildCommands implements CommandExecutor {

    private final GuildManager guildManager;
    private final HonorIntegration honorIntegration;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public GuildCommands(GuildManager guildManager, HonorIntegration honorIntegration) {
        this.guildManager = guildManager;
        this.honorIntegration = honorIntegration;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(miniMessage.stripTags("<red>Usage: /guild <join|leave> [guildName]</red>"));
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "join":
                if (args.length < 2) {
                    player.sendMessage(miniMessage.stripTags("<red>Usage: /guild join <BountyHunters|Outlaws></red>"));
                    return true;
                }

                String guildName = args[1].toLowerCase();
                int honor = honorIntegration.getHonor(player);

                if (guildName.equals("bountyhunters") && honor >= 10) {
                    guildManager.joinGuild(player, "Bounty Hunters", honor);
                    player.sendMessage(miniMessage.stripTags("<green>You have joined the <bold>Bounty Hunters Guild</bold>!</green>"));
                } else if (guildName.equals("outlaws") && honor <= -10) {
                    guildManager.joinGuild(player, "Outlaws", honor);
                    player.sendMessage(miniMessage.stripTags("<red>You have joined the <bold>Outlaws Guild</bold>!</red>"));
                } else {
                    player.sendMessage(miniMessage.stripTags("<yellow>You do not meet the honor requirements for this guild.</yellow>"));
                }
                break;

            case "leave":
                guildManager.leaveGuild(player);
                player.sendMessage(miniMessage.stripTags("<gray>You have left your guild.</gray>"));
                break;

            default:
                player.sendMessage(miniMessage.stripTags("<red>Unknown subcommand.</red>"));
                break;
        }

        return true;
    }

    public List<String> onTabComplete(String[] args) {
        if (args.length == 1) {
            return Arrays.asList("join", "leave");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
            return Arrays.asList("BountyHunters", "Outlaws");
        }
        return List.of();
    }
}

