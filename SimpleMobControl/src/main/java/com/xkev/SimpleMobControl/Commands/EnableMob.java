package com.xkev.SimpleMobControl.Commands;

import com.xkev.SimpleMobControl.Main;
import com.xkev.SimpleMobControl.MobConfig.Mobs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Command /enablemob <mob> to enable specific or all mobs
 */
public class EnableMob implements CommandExecutor {

    private Mobs mobs;
    private JavaPlugin javaPlugin;

    public EnableMob(JavaPlugin javaPlugin, Mobs mobs) {
        this.javaPlugin = javaPlugin;
        this.mobs = mobs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        }

        if (args.length == 1) {
            if (args[0].equals("all")) {
                mobs.enableAllMobs();
                if (player != null) {
                    player.sendMessage(Main.prefix + "Enabled all mobs!");
                } else {
                    javaPlugin.getLogger().info("Enabled all mobs!");
                }
            } else if (!mobs.getAvailableMobs().contains(args[0])) {
                if (player != null) {
                    player.sendMessage(Main.prefix + "Mob not available! (Case-Sensitive)");
                } else {
                    javaPlugin.getLogger().info("Mob not available! (Case-Sensitive)");
                }
            } else if (!mobs.getDisabledMobs().contains(args[0])) {
                if (player != null) {
                    player.sendMessage(Main.prefix + args[0] + " is not disabled!");
                } else {
                    javaPlugin.getLogger().info(args[0] + " is not disabled!");
                }
            } else {
                mobs.removeDisabledMob(args[0]);
                if (player != null) {
                    player.sendMessage(Main.prefix + args[0] + " was successfully removed from the list of disabled Mobs!");
                } else {
                    javaPlugin.getLogger().info(args[0] + " was successfully removed from the list of disabled Mobs!");
                }
            }
            return true;
        }

        return false;
    }
}
