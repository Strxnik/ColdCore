package it.coldcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("feed"))
            if(player.hasPermission("coldcore.feed")) {
                player.setFoodLevel(20);
                player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.GREEN + "Your hunger has been restored.");
            } else {
                player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.RED + "You don't have the permission.");
            }
        } else {
            System.out.println("[ColdCore] The command can only be executed by a Player.");
        }
        return true;
    }
}
