package io.github.usbharu.venriplugin.pin;

import io.github.usbharu.venriplugin.VenriPlugin;
import io.github.usbharu.venriplugin.pin.AbstractPin;
import io.github.usbharu.venriplugin.pin.Pin;
import io.github.usbharu.venriplugin.pin.PinAction;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PinCommand implements CommandExecutor {

  public PinCommand() {
  }


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player player)) {
      return false;
    }
    if (args.length == 0) {
      simplePin(player);
    } else if (args.length == 1) {
      oneArgs(player, args[0]);
    }else if (args.length == 2){
      twoArgs(args, player);
    }
    return true;
  }

  private void twoArgs(String[] args, Player player) {
    if(args[0].equals("remove")){
      try {
        PinAction.pins.remove(Integer.parseInt(args[1]));
      }catch (NumberFormatException e){
        PinAction.pins.removeIf(pin -> pin.getName().equals(args[1]));
      }
    }else if(args[0].equals("onetime")){
      PinAction.oneTimePin(args[1], player, player.getLocation());
    }
  }


  private void simplePin(Player player){
    PinAction.pinWithMessage(player, player.getLocation());
  }

  private void oneArgs(Player player,String arg){
    if(arg.equals("list")){
      List<Pin> pins = PinAction.pins;
      for (int i = 0, pinsSize = pins.size(); i < pinsSize; i++) {
        Pin pin = pins.get(i);
        player.sendMessage(i+": "+pin.getName() + " X:" + ((int) pin.getLocation().getX()) + " Y:"
            + ((int) pin.getLocation().getY()) + " Z:" + ((int) pin.getLocation().getZ()));
      }
    }else if(arg.equals("clear")){
      PinAction.pins.clear();
    }else if(arg.equals("onetime")){
      PinAction.oneTimePinWithMessage(player, player.getLocation());
    }else {
      PinAction.pin(arg, player, player.getLocation());
    }
  }
}
