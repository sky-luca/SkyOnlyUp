package net.sytoox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class starttp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Check if the player has the required permission
            if (player.hasPermission("onlyup.use")) {
                World lobbyWorld = Bukkit.getWorld("sytoox_onlyup");
                if (lobbyWorld != null) {
                    double x = 17.312;
                    double y = -58.00000;
                    double z = 33.832;
                    player.teleport(new Location(lobbyWorld, x, y, z));
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You have been teleported to the start of OnlyUp!");
                } else {
                    player.sendMessage(ChatColor.BOLD + " " + ChatColor.RED + "" + "There is no taxi available right now to bring you to the start of OnlyUp! Please contact support!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            }
            return true;
        } else {
            sender.sendMessage("This command can only be used by players.");
            return false;
        }
    }
}