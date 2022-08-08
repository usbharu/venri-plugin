package io.github.usbharu.venriplugin.navigate;

import io.github.usbharu.venriplugin.util.LocationAccessObject;
import io.github.usbharu.venriplugin.util.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DefaultNavigate extends Navigate {

  Player player = null;

  public DefaultNavigate(Location from, Location to) {
    super(from, to);
  }

  public DefaultNavigate(LocationAccessObject fromAccess, LocationAccessObject toAccess) {
    super(fromAccess, toAccess);
  }

  public DefaultNavigate(Location from, LocationAccessObject toAccess) {
    super(from, toAccess);
  }

  public DefaultNavigate(LocationAccessObject fromAccess, Location to) {
    super(fromAccess, to);
  }

  public DefaultNavigate(Player player,Location to){
    this(new LocationAccessObject(player),to);
    this.player = player;
  }

  protected boolean isEnable = true;

  @Override
  public void enable() {
    isEnable = true;
  }

  @Override
  public void disable() {
    isEnable = false;
  }

  @Override
  public boolean isEnable() {
    return isEnable;
  }

  @Override
  public void update() {
    Vector normalize = getTo().clone().subtract(getFrom()).toVector().normalize();
    Vector3 vector3 = new Vector3(normalize.getX(), normalize.getY(), normalize.getZ());
    getFrom().getWorld()
        .spawnParticle(Particle.DRAGON_BREATH, getFrom(), 0, vector3.getX(), vector3.getY(),
            vector3.getZ(), 1, null, true);
  }

  private String getString(Location location) {
    return location.getX() + ", " + location.getY() + ", " + location.getZ();
  }

  public Player getPlayer() {
    return player;
  }
}
