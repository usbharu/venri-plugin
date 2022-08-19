package io.github.usbharu.venriplugin2.pin;

import io.github.usbharu.venriplugin2.pin.factory.DefaultFactory;
import io.github.usbharu.venriplugin2.pin.factory.Factory;
import io.github.usbharu.venriplugin2.pin.pins.Pin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PinUtil {

  private static final Factory factory = new DefaultFactory();

  private PinUtil() {
  }

  public static Pin registerPin(String name, Player sender, Location location, Factory factory,
      String pinType) {
    return factory.create(pinType, name, sender, location);
  }

  public static Pin registerPin(String name, Player sender, Location location, String pinType) {
    return factory.create(pinType, name, sender, location);
  }
}
