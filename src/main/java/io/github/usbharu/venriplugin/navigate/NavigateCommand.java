package io.github.usbharu.venriplugin.navigate;

import io.github.usbharu.venriplugin.pin.Pin;
import io.github.usbharu.venriplugin.pin.PinAction;
import io.github.usbharu.venriplugin.util.LocationAccessObject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NavigateCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player player)) {
      return false;
    }
    if (args.length==2) {
      if(args[0].equals("pin")){
        navigateToPin(player, args[1]);
      }
    }else if(args.length==1){
      if(args[0].equals("stop")){
        for (Navigate navigate : NavigateAction.NAVIGATES) {
          if (navigate instanceof DefaultNavigate defaultNavigate) {
            if (defaultNavigate.getPlayer().equals(player)) {
              defaultNavigate.disable();
            }
          }
        }
      }else{
        navigateToPlayer(player, args[0]);
      }
    }
    return true;
  }

  private void navigateToPin(Player player,String pinName) {
    NavigateAction.startNavigate(player, PinAction.getPin(pinName).getLocation());
  }

  private void navigateToPlayer(Player player,String playerName){
    NavigateAction.startNavigate(new LocationAccessObject(player), new LocationAccessObject(Bukkit.getPlayer(playerName)));
  }
}
