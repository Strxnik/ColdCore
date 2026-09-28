package src.it.coldcore.java.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.ChatUtils;

public class Teleport implements CommandExecutor {

    private final main plugin;
    private final ChatUtils chatUtils;

    public Teleport(main plugin) {
        this.plugin = plugin;
        this.chatUtils = new ChatUtils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("coldcore.tp")) {
                if (cmd.getName().equalsIgnoreCase("tp")) {
                    if (args.length == 0) {
                        String message = chatUtils.getFormattedMessage("syntax-error",
                                ChatColor.RED + "To teleport yourself type '/tp <target>'.\nTo teleport others type '/tp <PlayerToSend> <Target>'");
                        sender.sendMessage(message);
                    } else if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        player.teleport(target.getLocation());
                    } else if (args.length == 2) {
                        Player playerToSend = Bukkit.getPlayer(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        playerToSend.teleport(target.getLocation());
                    } else {
                        //syntax error
                        String message = chatUtils.getFormattedMessage("syntax-error",
                                ChatColor.RED + "To teleport yourself type '/tp <target>'.\nTo teleport others type '/tp <PlayerToSend> <Target>'");
                        sender.sendMessage(message);
                    }
                }
            } else {
                String message = chatUtils.getFormattedMessage("no-permission");
                sender.sendMessage(message);
            }
        } else {
            System.out.println("[ColdCore] The command can only be executed by a Player.");
        }
        return true;
    }
}