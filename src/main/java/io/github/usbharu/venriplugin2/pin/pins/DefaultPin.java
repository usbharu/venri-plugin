package io.github.usbharu.venriplugin2.pin.pins;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DefaultPin extends AbstractPin {

  private @NotNull World world;

  public DefaultPin() {
  }

  public DefaultPin(String name, Player player, Location location) {
    super(name, location);
    super.location = location.clone();
    world = player.getWorld();
  }

  @Override
  public void setPlayer(Player player) {
    super.setPlayer(player);
    world = player.getWorld();
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  @Override
  public void showPin() {

    world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 0, 0, 0.1, 0, 20, null, true);

  }
}
