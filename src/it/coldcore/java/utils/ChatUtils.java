package src.it.coldcore.java.utils;

import org.bukkit.ChatColor;
import src.it.coldcore.java.main.main;

import java.util.Map;

public class ChatUtils {

    private final main plugin;
    public ChatUtils(main plugin) {
        this.plugin = plugin;
    }

    public String setPlaceholders(String message, Map<String, String> placeholders) {
        if(placeholders != null) {
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                message = message.replace(entry.getKey(), entry.getValue());
            }
        }
        return message;
    }

    public String getFormattedMessage(String path) {
        String message = plugin.getMessagesConfig().getString(path);

        if (message == null) {
            return "&cMessage not found." + path;
        }

        String prefix = plugin.getMessagesConfig().getString("prefix");
        message = prefix + message;

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getFormattedMessage(String path, Map<String, String> placeholders, boolean prefixed) {
        String message = plugin.getMessagesConfig().getString(path);
        message = setPlaceholders(message, placeholders);

        if (message == null) {
            return "&cMessage not found." + path;
        }

        if(prefixed) {
            String prefix = plugin.getMessagesConfig().getString("prefix");
            message = prefix + message;
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getFormattedMessage(String path, String customMsg) {
        String message = plugin.getMessagesConfig().getString(path);

        if (message == null) {
            return "&cMessage not found." + path;
        }

        String prefix = plugin.getMessagesConfig().getString("prefix");
        message = prefix + message;

        return ChatColor.translateAlternateColorCodes('&', message + customMsg);
    }

    public String getCustomFormattedMessage(String customMsg) {
        String message = plugin.getMessagesConfig().getString("prefix") + customMsg;
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}