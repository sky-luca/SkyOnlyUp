package net.sytoox.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FinishTeleport implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            Player player = event.getPlayer();
            World world = Bukkit.getWorld("sytoox_onlyup");
            if (world != null) {
                double x = 116.391;
                double y = 268.00000;
                double z = 104.495;
                Location teleportLocation = new Location(world, x, y, z);
                player.teleport(teleportLocation);
                player.sendMessage("You succesfull finish OnlyUp!");
            } else {
                player.sendMessage("Please contact a staff member there is something wrong with your teleport!");
            }
        }
    }

    public void registerEvents(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}