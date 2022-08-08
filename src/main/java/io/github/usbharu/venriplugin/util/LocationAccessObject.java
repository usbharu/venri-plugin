package io.github.usbharu.venriplugin.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class LocationAccessObject {
  private final Entity entity;

  public LocationAccessObject(Entity entity) {
    this.entity = entity;
  }

  public Location getLocation() {
    return entity.getLocation();
  }
}
