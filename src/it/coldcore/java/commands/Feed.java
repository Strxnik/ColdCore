package src.it.coldcore.java.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.ChatUtils;

import static src.it.coldcore.java.main.main.basePermission;

public class Feed implements CommandExecutor {

    private final ChatUtils chatUtils;
    String commandPermission = "feed";

    public Feed(main plugin) {
        this.chatUtils = new ChatUtils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("feed"))
            if(player.hasPermission(basePermission+commandPermission)) {
                player.setFoodLevel(20);
                String message = chatUtils.getCustomFormattedMessage(ChatColor.GREEN + "Your hunger has been restored.");
                player.sendMessage(message);
            } else {
                String message = chatUtils.getFormattedMessage("no-permission");
                player.sendMessage(message);
            }
        } else {
            System.out.println("[ColdCore] The command can only be executed by a Player.");
        }
        return true;
    }
}
