package io.github.usbharu.venriplugin2.pin.factory;

import io.github.usbharu.venriplugin2.pin.pins.DefaultPin;
import io.github.usbharu.venriplugin2.pin.pins.OneTimePin;
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
    } else if (owner.equals(ONE_TIME)) {
      pin = new OneTimePin();
    }
    return pin;
  }

  @Override
  protected Pin registerPin(Pin pin, String name, Player sender, Location location) {
    pin.setName(name);
    System.out.println("location = " + location);
    pin.setLocation(location.clone());
    pin.setPlayer(sender);
    System.out.println("pin = " + pin);
    return pin;
  }
}
