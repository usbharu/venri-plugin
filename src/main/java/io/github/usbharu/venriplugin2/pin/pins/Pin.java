package io.github.usbharu.venriplugin2.pin.pins;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Pin {

  void setLocation(Location location);

  void setName(String name);

  Player getPlayer();

  Location getLocation();

  String getName();

  void setPlayer(Player player);

  void showPin();

  boolean isToBeDestroyed();
}
