package io.github.usbharu.venriplugin2.pin.pins;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class OneTimePin extends DefaultPin {

  private int lifeTime = 300;

  public OneTimePin(String name, Player player, Location location) {
    super(name, player, location);
  }

  public OneTimePin(Player player, String name, Location location, int lifeTime) {
    super(name, player, location);
    this.lifeTime = lifeTime;
  }

  @Override
  public void showPin() {
    super.showPin();
    --lifeTime;
  }

  @Override
  public boolean isToBeDestroyed() {
    return lifeTime <= 0;
  }
}
