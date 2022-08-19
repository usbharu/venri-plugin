package io.github.usbharu.venriplugin2.pin.pins;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AbstractPin implements Pin {

  String name;
  Location location;

  Player player;

  public AbstractPin() {
  }

  public AbstractPin(String name, Location location) {
    this.name = name;
    this.location = location;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public Player getPlayer() {
    return player;
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
  public void setPlayer(Player player) {
    this.player = player;
  }

  @Override
  public void showPin() {

  }

  @Override
  public boolean isToBeDestroyed() {
    return false;
  }
}
