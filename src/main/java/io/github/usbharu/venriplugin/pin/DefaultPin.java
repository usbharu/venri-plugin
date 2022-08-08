package io.github.usbharu.venriplugin.pin;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DefaultPin extends AbstractPin {

  protected Location clone;
  protected Location clone2;

  protected World world;

  public DefaultPin(Player player,String str,Location location){
    super(str, location);
    clone = location.clone();
//    clone2 = clone.clone().add(0,40,0);
    world = player.getWorld();
  }

  @Override
  public void showPin() {
    world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, clone, 0, 0, 0.1, 0, 20, null, true);
//    world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, clone2, 0, 0, 0.1, 0, 20, null, true);
  }
}
