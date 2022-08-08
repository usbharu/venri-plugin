package io.github.usbharu.venriplugin.postplayerlocation;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PostPlayerLocation implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      if (args.length == 0) {
        Location location = ((Player) sender).getLocation();
        sender.getServer().broadcastMessage(sender.getName()+"は X:"+ ((int) location.getX()) +" Y:"+ ((int) location.getY())+" Z:"+ ((int) location.getZ())+"にいますよ!");
      }else if (args.length == 1){
        Player player = sender.getServer().getPlayer(args[0]);
        if (player == null) {
          sender.sendMessage("プレイヤーが見つかりませんでした");
          return true;
        }
        Location location = player.getLocation();
        sender.getServer().broadcastMessage(player.getName()+"は X:"+ ((int) location.getX())+" Y:"+ ((int) location.getY())+" Z:"+ ((int) location.getZ())+"にいますよ!");
      }
    }
    return true;
  }
}
