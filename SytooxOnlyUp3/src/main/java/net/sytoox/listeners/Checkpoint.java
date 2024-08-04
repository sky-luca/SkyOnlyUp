package net.sytoox.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.block.Action;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Checkpoint implements Listener {

    private final Map<UUID, Location> playerCheckpoints = new HashMap<>();
    private final Map<UUID, Long> playerCooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 5000; // Cooldown time in milliseconds (e.g., 5000ms = 5 seconds)

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.POLISHED_BLACKSTONE_PRESSURE_PLATE) {
            Player player = event.getPlayer();
            UUID playerId = player.getUniqueId();

            long currentTime = System.currentTimeMillis();
            if (playerCooldowns.containsKey(playerId) && currentTime - playerCooldowns.get(playerId) < COOLDOWN_TIME) {
                return; // If the player is within the cooldown time, do nothing
            }

            Location checkpointLocation = event.getClickedBlock().getLocation();
            playerCheckpoints.put(playerId, checkpointLocation);
            playerCooldowns.put(playerId, currentTime);
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You have set a new checkpoint!!");
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Location checkpoint = playerCheckpoints.get(player.getUniqueId());
        if (checkpoint != null) {
            event.setRespawnLocation(checkpoint);
            player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "You have been respawned at your checkpoint.");
        }
    }

    public void registerEvents(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
