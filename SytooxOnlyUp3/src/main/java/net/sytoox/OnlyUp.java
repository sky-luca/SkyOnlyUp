package net.sytoox;


import net.sytoox.commands.finishtp;
import net.sytoox.commands.starttp;
import net.sytoox.extras.FinishGift;
import net.sytoox.listeners.Checkpoint;
import net.sytoox.listeners.FinishTeleport;
import org.bukkit.plugin.java.JavaPlugin;

public final class OnlyUp extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Der OnlyUp Gamemode wurde erfolgreich gestartet!");
        getCommand("onlyup-finish").setExecutor(new finishtp());
        getCommand("onlyup-start").setExecutor(new starttp());
        getCommand("onlyup-finish-present").setExecutor(new FinishGift());
        Checkpoint checkpointListener = new Checkpoint();
        checkpointListener.registerEvents(this);
        FinishTeleport finishTeleport = new FinishTeleport();
        finishTeleport.registerEvents(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Der OnlyUp Gamemode wurde erfolgreich gestoppt!");
    }
}
