package src.it.coldcore.java.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.ChatUtils;

import static src.it.coldcore.java.main.main.basePermission;

public class Gamemode implements CommandExecutor {

    private final ChatUtils chatUtils;
    String commandPermission = "gamemode.";

    public Gamemode(main plugin) {
        this.chatUtils = new ChatUtils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("gamemode")) {
                if (player.hasPermission(basePermission+commandPermission)) {
                    if (args.length < 2) {
                        switch (args[0]) {
                            case "0" -> {
                                if (player.hasPermission(basePermission + commandPermission + "survival")) {
                                    player.setGameMode(GameMode.SURVIVAL);
                                    String message = chatUtils.getCustomFormattedMessage(ChatColor.GRAY + "The gamemode has been set to SURVIVAL.");
                                    sender.sendMessage(message);
                                } else {
                                    String message = chatUtils.getFormattedMessage("no-permission");
                                    sender.sendMessage(message);
                                }
                            }
                            case "1" -> {
                                if (player.hasPermission(basePermission + commandPermission + "creative")) {
                                    player.setGameMode(GameMode.CREATIVE);
                                    String message = chatUtils.getCustomFormattedMessage(ChatColor.GRAY + "The gamemode has been set to CREATIVE.");
                                    sender.sendMessage(message);
                                } else {
                                    String message = chatUtils.getFormattedMessage("no-permission");
                                    sender.sendMessage(message);
                                }
                            }
                            case "2" -> {
                                if (player.hasPermission(basePermission + commandPermission + "adventure")) {
                                    player.setGameMode(GameMode.ADVENTURE);
                                    String message = chatUtils.getCustomFormattedMessage(ChatColor.GRAY + "The gamemode has been set to ADVENTURE.");
                                    sender.sendMessage(message);
                                } else {
                                    String message = chatUtils.getFormattedMessage("no-permission");
                                    sender.sendMessage(message);
                                }
                            }
                            case "3" -> {
                                if (player.hasPermission(basePermission + commandPermission + "spectator")) {
                                    player.setGameMode(GameMode.SPECTATOR);
                                    String message = chatUtils.getCustomFormattedMessage(ChatColor.GRAY + "The gamemode has been set to SPECTATOR.");
                                    sender.sendMessage(message);
                                } else {
                                    String message = chatUtils.getFormattedMessage("no-permission");
                                    sender.sendMessage(message);
                                }
                            }
                            default -> {
                                String message = chatUtils.getFormattedMessage(
                                        "syntax-error",
                                        ChatColor.RED + "Type '/gm <Number from 0 to 3>'.");
                                sender.sendMessage(message);
                            }
                        }
                    } else {
                        String message = chatUtils.getFormattedMessage(
                                "syntax-error",
                                ChatColor.RED + "Type '/gm <Number from 0 to 3>'.");
                        sender.sendMessage(message);
                    }
                } else {
                    String message = chatUtils.getFormattedMessage("no-permission");
                    sender.sendMessage(message);
                }
            }
        }else{
            System.out.println("[ColdCore] The command can only be executed by a Player.");
        }
        return true;
    }
}