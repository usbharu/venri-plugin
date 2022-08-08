package io.github.usbharu.venriplugin.pin;

import io.github.usbharu.venriplugin.VenriPlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

public class PinAction implements Listener {

  protected static final VenriPlugin plugin =
      ((VenriPlugin) Bukkit.getPluginManager().getPlugin("Venri-Plugin"));

  public static final AtomicBoolean flag = new AtomicBoolean(false);
  public static final List<Pin> pins = new ArrayList<>();

  public static final int pinDistance = 30;

  static {
    new BukkitRunnable() {
      @Override
      public void run() {
        pins.forEach(Pin::showPin);
        pins.removeIf(Pin::isToBeDestroyed);
        flag.set(false);
      }
    }.runTaskTimer(plugin, 0, 1);
  }


  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    if (flag.get()) {
      return;
    }
    flag.set(true);
    Player player = event.getPlayer();

    if (event.getItem() != null) {
      return;
    }
    if (event.getAction() == Action.RIGHT_CLICK_AIR
        || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      return;
    }
    if (!player.isSneaking()) {
      return;
    }
    RayTraceResult rayTraceResult = player.rayTraceBlocks(pinDistance);
    if (rayTraceResult == null) {
      return;
    }

    if (rayTraceResult.getHitBlock() != null) {
      Block hitBlock = rayTraceResult.getHitBlock();
      Location location = hitBlock.getLocation();
      oneTimePin(hitBlock.getType().getKey().getKey(), player, location);
      player.getServer().broadcastMessage(
          player.getDisplayName() + " pinned at " + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ());
    }

  }

  public static void pinWithMessage(Player player, Location location) {
    player.getServer().broadcastMessage(
        player.getName() + "が X:" + ((int) location.getX()) + " Y:" + ((int) location.getY())
            + " Z:" + ((int) location.getZ()) + "にピンを刺しましたよ!");
    pin(player.getName() + "のピン", player, location);
  }

  public static void pin(String str, Player player, Location location) {
    pins.add(new DefaultPin(player, str, location));
//    pins.add(new OneTimePin(player,str,location));
  }

  public static void oneTimePin(String str, Player player, Location location) {
    pins.add(new OneTimePin(player, str, location, 300));
  }

  public static void oneTimePin(String str, Player player, Location location, int lifeTime) {
    pins.add(new OneTimePin(player, str, location, lifeTime));
  }

  public static void oneTimePinWithMessage(Player player, Location location) {
    player.getServer().broadcastMessage(
        player.getName() + "が X:" + ((int) location.getX()) + " Y:" + ((int) location.getY())
            + " Z:" + ((int) location.getZ()) + "にピンを刺しましたよ!");
    pins.add(new OneTimePin(player, player.getName() + "のピン", location));
  }

  public static Pin getPin(int index) {
    return pins.get(index);
  }

  public static Pin getPin(String name){
    for (Pin pin : pins) {
      if (pin.getName().equals(name)) {
        return pin;
      }
    }
    try {
      return getPin(Integer.parseInt(name));
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
