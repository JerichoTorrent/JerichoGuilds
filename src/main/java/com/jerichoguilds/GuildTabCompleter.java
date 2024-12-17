package com.jerichoguilds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;

public class GuildTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("join");
            completions.add("leave");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
            completions.add("BountyHunters");
            completions.add("Outlaws");
        }
        return completions;
    }
}
