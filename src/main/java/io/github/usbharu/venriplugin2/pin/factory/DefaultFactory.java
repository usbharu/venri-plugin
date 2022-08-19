package io.github.usbharu.venriplugin2.pin.factory;

import io.github.usbharu.venriplugin2.pin.pins.DefaultPin;
import io.github.usbharu.venriplugin2.pin.pins.Pin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DefaultFactory extends Factory {

  public static final String DEFAULT = "default";
  public static final String ONE_TIME = "one_time";

  @Override
  protected Pin createPin(String owner) {
    Pin pin = null;
    if (owner.equals(DEFAULT)) {
      pin = new DefaultPin();
    }
    return pin;
  }

  @Override
  protected void registerPin(Pin pin, String name, Player sender, Location location) {
    pin.setName(name);
    pin.setLocation(location);
    pin.setPlayer(sender);
  }
}
