package io.github.usbharu.venriplugin2.pin.pins;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DefaultPin extends AbstractPin {

  private @NotNull Location clone;
  private @NotNull World world;

  public DefaultPin() {
  }

  public DefaultPin(String name, Player player, Location location) {
    super(name, location);
    clone = location.clone();
    world = player.getWorld();
  }

  public Location getClone() {
    return clone;
  }

  public void setClone(Location clone) {
    this.clone = clone;
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  @Override
  public void showPin() {
    world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, clone, 0, 0, 0.1, 0, 20, null, true);
  }
}
