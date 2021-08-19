package it.coldcore.main;

import it.coldcore.commands.Feed;
import it.coldcore.commands.Heal;
import it.coldcore.commands.gamemode.Gamemode;
import it.coldcore.commands.help.Help;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    public static String version = "1.0";

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "Plugin enabled!");
        getCommand("feed").setExecutor(new Feed());
        getCommand("heal").setExecutor(new Heal());
        getCommand("gm").setExecutor(new Gamemode());
        getCommand("coldcore").setExecutor(new Help());
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "Plugin disabled!");
    }
}