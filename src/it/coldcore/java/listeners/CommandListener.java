package src.it.coldcore.java.listeners;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.ChatUtils;

import java.util.Arrays;

public class CommandListener implements Listener {

    private final main plugin;
    private final ChatUtils chatUtils;

    public CommandListener(main plugin) {
        this.plugin = plugin;
        this.chatUtils = new ChatUtils(plugin);
    }

    @EventHandler
    public void onCommandEvent(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        String[] args = message.split(" ");
        args[0] = args[0].substring(1);
        args[0] = args[0].toLowerCase();

        if(args[0].equals("gamemode")) {
            e.setCancelled(true);
            if(args.length > 1 && args.length <= 3) {
                CommandExecutor executor = plugin.getCommand("gamemode").getExecutor();
                executor.onCommand(e.getPlayer(), plugin.getCommand("gamemode"), "gamemode", Arrays.copyOfRange(args, 1, args.length));
            } else {
                player.sendMessage(chatUtils.getFormattedMessage("syntax-error"));
            }
        } else {
            checkDisabledCommand(args[0], player, e);
        }

    }
    private void checkDisabledCommand(String cmd, Player player, PlayerCommandPreprocessEvent e) {
        if(plugin.cmdBlocked.contains(cmd)) {
            e.setCancelled(true);
            String response = chatUtils.getFormattedMessage("command-not-enabled");
            player.sendMessage(response);
        }
    }
}
