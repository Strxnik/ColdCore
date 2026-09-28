package src.it.coldcore.java.main;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import src.it.coldcore.java.commands.*;
import src.it.coldcore.java.listeners.BackpackListener;
import src.it.coldcore.java.listeners.CommandListener;
import src.it.coldcore.java.listeners.JoinQuitPlayerListener;
import src.it.coldcore.java.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin {

    public static String basePermission = "coldcore.";
    public static String adminPermission = "admin";
    public static String version = "1.1.0";

    public List<String> cmdBlocked = new ArrayList<>();

    private FileConfiguration msgConfig;
    private File messagesFile;

    //TO-DO: messages.yml

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "Plugin enabled!");

        saveDefaultConfig();
        loadMessagesConfig();
        registerCommands();
        registerEvents();
        saveCmdBlocked();

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

    private void registerCommands() {
        getCommand("feed").setExecutor(new Feed(this));
        getCommand("heal").setExecutor(new Heal(this));
        getCommand("gamemode").setExecutor(new Gamemode(this));
        getCommand("coldcore").setExecutor(new ColdcoreCommand(this));
        getCommand("tp").setExecutor(new Teleport(this));
        getCommand("backpack").setExecutor(new Backpack(this));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinQuitPlayerListener(this), this);
        pm.registerEvents(new BackpackListener(this), this);
        pm.registerEvents(new CommandListener(this), this);
    }

    public void loadMessagesConfig() {
        messagesFile = new File(getDataFolder(), "messages.yml");

        if(!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }

        msgConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getMessagesConfig() {
        return this.msgConfig;
    }

    public void saveCmdBlocked() {
        cmdBlocked.clear();
        ConfigurationSection section = getConfig().getConfigurationSection("features");
        for(String feature : section.getKeys(false)) {
            switch(feature) {
                case "backpacks":
                    cmdBlocked.add("backpack");
                    break;
            }
        }
    }
}