package io.github.usbharu.venriplugin.navigate;

import io.github.usbharu.venriplugin.VenriPlugin;
import io.github.usbharu.venriplugin.util.LocationAccessObject;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginBase;
import org.bukkit.scheduler.BukkitRunnable;

public class NavigateAction {

  protected static final VenriPlugin plugin =
      ((VenriPlugin) Bukkit.getPluginManager().getPlugin("Venri-Plugin"));

  public static final List<Navigate> NAVIGATES = new ArrayList<>();

  static {
    new BukkitRunnable() {
      @Override
      public void run() {
        for (Navigate navigate : NAVIGATES) {
          if (navigate.isEnable()) {
            navigate.update();
          }
        }
      }
    }.runTaskTimer(plugin, 0, 2);
  }

  public static void startNavigate(Location start, Location end) {
    NAVIGATES.add(new DefaultNavigate(start, end));
  }

  public static void startNavigate(LocationAccessObject from,Location to){
    NAVIGATES.add(new DefaultNavigate(from, to));
  }

  public static void startNavigate(LocationAccessObject from,LocationAccessObject to){
    NAVIGATES.add(new DefaultNavigate(from,to));
  }

  public static void startNavigate(Player player,Location to){
    NAVIGATES.add(new DefaultNavigate(player,to));
  }
}
