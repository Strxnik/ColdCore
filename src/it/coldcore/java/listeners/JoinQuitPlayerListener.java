package src.it.coldcore.java.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import src.it.coldcore.java.main.main;
import src.it.coldcore.java.utils.ChatUtils;

import java.util.Map;

public class JoinQuitPlayerListener implements Listener {

    private final ChatUtils chatUtils;

    public JoinQuitPlayerListener(main plugin) {
        this.chatUtils = new ChatUtils(plugin);
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(chatUtils.getFormattedMessage("join-message", Map.of(
                "%player%", e.getPlayer().getDisplayName()
        ), false));
    }

    @EventHandler
    public void PlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage(chatUtils.getFormattedMessage("quit-message", Map.of(
                "%player%", e.getPlayer().getDisplayName()
        ), false));
    }
}
