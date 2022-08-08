package io.github.usbharu.venriplugin.pin;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class OneTimePin extends DefaultPin {

  public OneTimePin(Player player,String name, Location location) {
    super(player,name, location);
  }

  public OneTimePin(Player player, String str, Location location, int lifeTime) {
    super(player, str, location);
    this.lifeTime = lifeTime;
  }

  private int lifeTime = 300;

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
