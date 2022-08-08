package io.github.usbharu.venriplugin.pin;

import org.bukkit.Location;

public abstract class AbstractPin implements Pin{

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
  public abstract void showPin();

  @Override
  public boolean isToBeDestroyed() {
    return false;
  }
}
