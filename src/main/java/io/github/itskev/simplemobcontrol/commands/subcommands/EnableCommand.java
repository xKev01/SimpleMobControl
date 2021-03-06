package io.github.itskev.simplemobcontrol.commands.subcommands;

import io.github.itskev.simplemobcontrol.config.MobsService;
import io.github.itskev.simplemobcontrol.config.SaveMobConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import static io.github.itskev.simplemobcontrol.util.MessageUtil.sendMessage;

@RequiredArgsConstructor
public class EnableCommand {

  private final Plugin plugin;
  private final MobsService mobsService;

  public void enableAllMobs(CommandSender commandSender, String world) {
    mobsService.getWorlds().get(world).enableAllMobs();
    new SaveMobConfig(plugin, mobsService);
    sendMessage(commandSender, "Enabled all mobs in world " + world + "!");
  }

  public void enable(CommandSender sender, String[] args, String world) {
    if (args.length < 2 || !mobsService.getWorlds().get(world).getAvailableMobs().contains(args[1])) {
      sendMessage(sender, "Mob not available!");
    } else if (!mobsService.getWorlds().get(world).getDisabledMobs().contains(args[1])) {
      sendMessage(sender, args[1] + " is not disabled!");
    } else {
      mobsService.getWorlds().get(world).removeDisabledMob(args[1]);
      new SaveMobConfig(plugin, mobsService);
      sendMessage(sender, args[1] + " was successfully removed from the list of disabled Mobs!");
    }
  }
}
