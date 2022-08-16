package io.github.usbharu.venriplugin2.pin;

import org.bukkit.Location;

public class AbstractPin implements Pin {

  String name;
  Location location;

  public AbstractPin(String name, Location location) {
    this.name = name;
    this.location = location;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void showPin() {

  }

  @Override
  public boolean isToBeDestroyed() {
    return false;
  }
}
