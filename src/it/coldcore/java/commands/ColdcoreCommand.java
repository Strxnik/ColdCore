package src.it.coldcore.java.commands;

import src.it.coldcore.java.main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import src.it.coldcore.java.utils.ChatUtils;

import java.io.File;

import static src.it.coldcore.java.main.main.adminPermission;
import static src.it.coldcore.java.main.main.basePermission;

public class ColdcoreCommand implements CommandExecutor {

    private final main plugin;
    private final ChatUtils chatUtils;
    public ColdcoreCommand(main plugin) {
        this.plugin = plugin;
        this.chatUtils = new ChatUtils(plugin);
    }

    //TO-DO: Aggiornare messaggi help comandi.

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("coldcore")) {
         if(sender instanceof Player) {
             Player player = (Player) sender;
             if(args.length > 0) {
                 if (args[0].equalsIgnoreCase("help")) {
                     player.sendMessage(getHelpMessage());
                 } else if(args[0].equalsIgnoreCase("reload")) {
                     if (player.hasPermission(basePermission + adminPermission)) {
                         plugin.reloadConfig(); //Ricarico plugin.yml
                         plugin.saveCmdBlocked();
                         if(!new File(plugin.getDataFolder(), "config.yml").exists()) {
                             plugin.saveResource("config.yml", false);
                         }
                         plugin.loadMessagesConfig(); //Ricarico messages.yml
                         String message = chatUtils.getCustomFormattedMessage("&aConfig file has been reloaded.");
                         sender.sendMessage(message);
                     } else {
                         String message = chatUtils.getFormattedMessage("no-permission");
                         sender.sendMessage(message);
                     }
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
    
    private String getHelpMessage() {
        String message;
        message =
                ChatColor.AQUA + "ColdCore version " + ChatColor.YELLOW + main.version + "\n" +
                ChatColor.GRAY + "➥ " + ChatColor.RED + "'/help' " + ChatColor.GRAY + "Show this message." + "\n" +
                ChatColor.GRAY + "➥ " + ChatColor.RED + "'/feed' " + ChatColor.GRAY + "Restore your hunger." + "\n" +
                ChatColor.GRAY + "➥ " + ChatColor.RED + "'/heal' " + ChatColor.GRAY + "Restore your life." + "\n" +
                ChatColor.GRAY + "➥ " + ChatColor.RED + "'/gm' " + ChatColor.GRAY + "Change your gamemode." + "\n" +
                ChatColor.GRAY + "➥ " + ChatColor.RED + "'/backpack' " + ChatColor.GRAY + "Open your backpack.";
        return message;
    }
}
