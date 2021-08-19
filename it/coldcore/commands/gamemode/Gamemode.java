package it.coldcore.commands.gamemode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("gm")) {
                if (args.length > 0) {
                    if (args[0].equals("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                    } else if (args[0].equals("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                    } else if (args[0].equals("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                    } else if (args[0].equals("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                    } else {
                        player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.RED + "The syntax of the command is wrong. " + ChatColor.GRAY + "Type '/gm <Number from 0 to 3>'.");
                    }
                } else {
                    player.sendMessage(ChatColor.AQUA + "ColdCore" + ChatColor.GRAY + " » " + ChatColor.RED + "The syntax of the command is wrong. " + ChatColor.GRAY + "Type '/gm <Number from 0 to 3>'.");
                }
            }
        }else{
            System.out.println("[ColdCore] The command can only be executed by a Player.");
        }
        return true;
    }
}
