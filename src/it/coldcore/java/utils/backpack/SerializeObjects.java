package src.it.coldcore.java.utils.backpack;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class SerializeObjects {

    public static String itemStackToBase64(ItemStack[] items) throws IllegalStateException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BukkitObjectOutputStream data = new BukkitObjectOutputStream(out);

            data.writeInt(items.length);

            for(ItemStack item : items) {
                data.writeObject(item);
            }

            data.close();

            return Base64.getEncoder().encodeToString(out.toByteArray());
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("[ColdCore] " + e.getMessage());
            throw new IllegalStateException("A problem has occurred, check the console.");
        }
    }

    public static ItemStack[] itemStackFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(data));
            BukkitObjectInputStream input = new BukkitObjectInputStream(in);

            ItemStack[] items = new ItemStack[input.readInt()];

            for(int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) input.readObject();
            }

            input.close();

            return items;
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("ColdCore: " + e.getMessage());
            throw new IOException("A problem has occurred, check the console.");
        }
    }

}
