package io.github.usbharu.venriplugin2.pin.factory;

import io.github.usbharu.venriplugin2.pin.pins.Pin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Factory {

  public final Pin create(String owner, String name, Player sender, Location location) {
    Pin pin = createPin(owner);
    registerPin(pin, name, sender, location);
    return pin;
  }


  protected abstract Pin createPin(String owner);

  protected abstract void registerPin(Pin pin, String name, Player sender, Location location);

}
