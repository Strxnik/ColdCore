package src.it.coldcore.java.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.backpack.BackpackHolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static src.it.coldcore.java.utils.backpack.SerializeObjects.itemStackToBase64;

public class BackpackListener implements Listener {

    private final main plugin;
    public BackpackListener(main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof BackpackHolder) {
            ItemStack[] items = e.getInventory().getContents();
            String encoded = itemStackToBase64(items);

            String uuid = e.getPlayer().getUniqueId().toString();
            File data = new File(plugin.getDataFolder() + "/backpacks", uuid + ".yml");

            writeFile(data, encoded);
        }
    }

    private void writeFile(File file, String content) {
        try {
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            Files.writeString(file.toPath(), content,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[ColdCore] " + e.getMessage());
        }
    }
}