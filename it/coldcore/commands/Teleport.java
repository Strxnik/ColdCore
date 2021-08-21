package it.coldcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("coldcore.tp")) {
                if (cmd.getName().equalsIgnoreCase("tp")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.RED + "The syntax of the command is wrong. " + ChatColor.GRAY + "To teleport yourself type '/tp <target>'.\nTo teleport others type '/tp <PlayerToSend> <Target>'");
                    } else if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        player.teleport(target.getLocation());
                    } else if (args.length == 2) {
                        Player playerToSend = Bukkit.getPlayer(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        playerToSend.teleport(target.getLocation());
                    } else {
                        player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.RED + "The syntax of the command is wrong. " + ChatColor.GRAY + "To teleport yourself type '/tp <target>'.\nTo teleport others type '/tp <PlayerToSend> <Target>'");
                    }
                }
            } else {
                player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.RED + "You don't have the permission.");
            }
        } else {
            System.out.println("[ColdCore] The command can only be executed by a Player.");
        }
        return true;
    }
}
