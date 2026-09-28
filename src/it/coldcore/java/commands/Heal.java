package src.it.coldcore.java.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.ChatUtils;

import static src.it.coldcore.java.main.main.basePermission;

public class Heal implements CommandExecutor {

    private final ChatUtils chatUtils;
    String commandPermission = "heal";

    public Heal(main plugin) {
        this.chatUtils = new ChatUtils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String text, String[] arg) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("heal")) {
                if (player.hasPermission(basePermission+commandPermission)) {
                    player.setHealth(20.0);
                    String message = chatUtils.getCustomFormattedMessage(ChatColor.GREEN + "Your life has been restored.");
                    player.sendMessage(message);
                } else {
                    String message = chatUtils.getFormattedMessage("no-permission");
                    player.sendMessage(message);
                }
            }
        } else {
            System.out.println("The command can only be executed by a Player");
        }
        return true;
    }
}
