package it.coldcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender player, Command command, String text, String[] arg) {
        if (player instanceof Player) {
            Player user = (Player) player;
            if (command.getName().equalsIgnoreCase("heal")) {
                if (user.hasPermission("coldcore.heal")) {
                    user.setHealth(20.0);
                    player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.GREEN + "Your life has been restored.");
                } else {
                    user.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.RED + "You don't have the permission.");
                }
            }
        } else {
            System.out.println("The command can only be executed by a Player");
        }
        return true;
    }
}
