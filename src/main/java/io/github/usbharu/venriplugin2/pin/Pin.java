package io.github.usbharu.venriplugin2.pin;

import org.bukkit.Location;

public interface Pin {

  Location getLocation();

  String getName();

  void showPin();

  boolean isToBeDestroyed();
}
