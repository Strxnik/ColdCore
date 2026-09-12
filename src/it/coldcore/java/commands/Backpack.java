package src.it.coldcore.java.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import src.it.coldcore.java.listeners.BackpackListener;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.backpack.BackpackHolder;
import src.it.coldcore.java.utils.ChatUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import static src.it.coldcore.java.main.main.basePermission;
import static src.it.coldcore.java.utils.backpack.SerializeObjects.itemStackFromBase64;

public class Backpack implements CommandExecutor {

    private final main plugin;
    private final ChatUtils chatUtils;
    private static String commandPermission = "backpack";
    private static String adminPermission = "admin";

    public Backpack(main plugin) {
        this.plugin = plugin;
        this.chatUtils = new ChatUtils(plugin);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("backpack")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(basePermission + commandPermission)) {
                        Inventory backpack = setBackpackContent(getBackpackContent(player.getUniqueId().toString()));
                        player.openInventory(backpack);
                    } else {
                        chatUtils.getFormattedMessage("no-permission");
                    }
                } else {
                    sender.sendMessage("This command can only be executed by players.");
                }
            } else {
                if(args[0].equalsIgnoreCase("clear")) {
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        if(player.hasPermission(basePermission + adminPermission)) {
                            File file = new File(plugin.getDataFolder() + "/backpacks/" + player.getUniqueId() + ".yml");
                            if(file.exists()) {
                                file.delete();
                            }
                            String message = chatUtils.getCustomFormattedMessage("&aBackpack has been cleared.");
                            sender.sendMessage(message);
                        }
                    }
                }
            }
        }
        return true;
    }

    private Inventory setBackpackContent(ItemStack[] items) {
        Inventory backpack =  Bukkit.createInventory(new BackpackHolder(), 27, "Backpack");
        backpack.setContents(items);
        return backpack;
    }

    private ItemStack[] getBackpackContent(String uuid) {
        File file = new File(plugin.getDataFolder() + "/backpacks", uuid + ".yml");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                return itemStackFromBase64(builder.toString());
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("[ColdCore] " + e.getMessage());
            }
        }
        return new ItemStack[0];
    }
}