package io.github.usbharu.venriplugin.navigate;

import io.github.usbharu.venriplugin.util.LocationAccessObject;
import org.bukkit.Location;

public abstract class Navigate {

  private final Location from;
  private final Location to;

  private final LocationAccessObject fromAccess;
  private final LocationAccessObject toAccess;
  protected Navigate(Location from, Location to) {
    this.from = from;
    this.to = to;
    this.fromAccess = null;
    this.toAccess = null;
  }

  protected Navigate(LocationAccessObject fromAccess, LocationAccessObject toAccess) {
    this.fromAccess = fromAccess;
    this.toAccess = toAccess;
    this.to = null;
    this.from = null;
  }

  protected Navigate(Location from, LocationAccessObject toAccess) {
    this.from = from;
    this.toAccess = toAccess;
    this.to = null;
    this.fromAccess = null;
  }

  protected Navigate(LocationAccessObject fromAccess, Location to) {
    this.fromAccess = fromAccess;
    this.to = to;
    this.toAccess = null;
    this.from = null;
  }

  public abstract void enable();
  public abstract void disable();
  public abstract boolean isEnable();
  public abstract void update();

  protected Location getFrom() {
    if (fromAccess != null) {
      return fromAccess.getLocation();
    }
    return from;
  }

  protected Location getTo() {
    if (toAccess != null) {
      return toAccess.getLocation();
    }
    return to;
  }
}
