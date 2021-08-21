package it.coldcore.main;

import it.coldcore.commands.*;
import it.coldcore.events.Events;
import it.coldcore.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    public static String version = "1.0.1";

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "Plugin enabled!");
        registerCommands();
        registerEvents();
        new UpdateChecker(this, 95470).getLatestVersion(version -> {
            if(this.getDescription().getVersion().equalsIgnoreCase(version)) {
                System.out.println("[ColdCore] Plugin is up to date.");
            } else {
                System.out.println("[ColdCore] There is an update available.");
            }
        });
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "Plugin disabled!");
    }

    public void registerCommands() {
        getCommand("feed").setExecutor(new Feed());
        getCommand("heal").setExecutor(new Heal());
        getCommand("gm").setExecutor(new Gamemode());
        getCommand("coldcore").setExecutor(new Help());
        getCommand("tp").setExecutor(new Teleport());
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new Events(), this);
    }
}