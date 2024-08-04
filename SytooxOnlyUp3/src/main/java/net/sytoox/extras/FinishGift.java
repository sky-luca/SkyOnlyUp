package net.sytoox.extras;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashSet;
import java.util.UUID;

public class FinishGift implements CommandExecutor {
    private final HashSet<UUID> usedCommand = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        // Check if the player has already used the command
        if (usedCommand.contains(playerUUID)) {
            player.sendMessage(ChatColor.RED + "You have already used this command.");
            return true;
        }

        // Idea 1: Item as a reward
        ItemStack giftItem = new ItemStack(Material.DIAMOND); // Choose an appropriate item as a reward
        ItemMeta meta = giftItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.AQUA + "Special Reward");
            giftItem.setItemMeta(meta);
        }
        player.getInventory().addItem(giftItem);

        // Idea 2: Permission as a reward
        PermissionAttachment attachment = player.addAttachment(Bukkit.getPluginManager().getPlugin("SytooxOnlyUp")); // Ensure "SytooxOnlyUp" matches the name in your plugin.yml
        attachment.setPermission("finishOnlyUp.use", true);
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You have got your gift, for completing the OnlyUp Gamemode of Sytoox.net!");

        // Mark the command as used for this player
        usedCommand.add(playerUUID);

        return true;
    }
}
