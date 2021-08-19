package it.coldcore.commands.help;

import it.coldcore.main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("coldcore")) {
         if(sender instanceof Player) {
             Player player = (Player) sender;
             if(args.length > 0) {
                 if (args[0].equals("help")) {
                     player.sendMessage(ChatColor.AQUA + "ColdCore version " + ChatColor.YELLOW + main.version);
                     player.sendMessage(ChatColor.GRAY + "➥ " + ChatColor.RED + "'/help' " + ChatColor.GRAY + "Show this message.");
                     player.sendMessage(ChatColor.GRAY + "➥ " + ChatColor.RED + "'/feed' " + ChatColor.GRAY + "Restore your hunger.");
                     player.sendMessage(ChatColor.GRAY + "➥ " + ChatColor.RED + "'/heal' " + ChatColor.GRAY + "Restore your life.");
                     player.sendMessage(ChatColor.GRAY + "➥ " + ChatColor.RED + "'/gm' " + ChatColor.GRAY + "Change your gamemode.");
                 } else {
                     player.sendMessage(ChatColor.AQUA + "ColdCore version " + ChatColor.YELLOW + main.version + ChatColor.GRAY + "\n➥ Type " + ChatColor.RED + "'/coldcore help' " + ChatColor.GRAY + "for more information.");
                 }
             } else {
                 player.sendMessage(ChatColor.AQUA + "ColdCore version " + ChatColor.YELLOW + main.version + ChatColor.GRAY + "\n➥ Type " + ChatColor.RED + "'/coldcore help' " + ChatColor.GRAY + "for more information.");
                }
            } else {
             System.out.println("[ColdCore] The command can only be executed by a Player.");
            }
        }
        return true;
    }
}
