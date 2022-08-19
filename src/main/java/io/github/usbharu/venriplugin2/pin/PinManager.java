package io.github.usbharu.venriplugin2.pin;

import io.github.usbharu.venriplugin2.pin.pins.Pin;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PinManager {

  public static final List<Pin> pinList = new ArrayList<>();
  private static final PinManager singleton = new PinManager();
  private static final Plugin PLUGIN = Bukkit.getPluginManager().getPlugin("Venri-Plugin2");

  static {
    new BukkitRunnable() {

      @Override
      public void run() {
        pinList.forEach(Pin::showPin);
        pinList.removeIf(Pin::isToBeDestroyed);
      }
    }.runTaskTimer(PLUGIN, 0, 1);
  }

  private PinManager() {
  }

  public static PinManager getInstance() {
    return singleton;
  }
}
