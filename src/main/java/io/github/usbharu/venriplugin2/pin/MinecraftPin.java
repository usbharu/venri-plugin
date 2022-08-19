package io.github.usbharu.venriplugin2.pin;

import static io.github.usbharu.venriplugin2.VenriPlugin2.CONFIGURATION;

import io.github.usbharu.venriplugin2.pin.factory.DefaultFactory;
import io.github.usbharu.venriplugin2.pin.pins.Pin;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;

public class MinecraftPin implements Listener {

  public static final List<Pin> PIN_LIST = new ArrayList<>();

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (event.getItem() != null) {
      return;
    }
    if (event.getAction() == Action.RIGHT_CLICK_AIR
        || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      return;
    }
    if (!player.isSneaking()) {
      return;
    }
    RayTraceResult rayTraceResult = player.rayTraceBlocks(
        CONFIGURATION().getConfiguration().getInt("Server.Functions.Pin.distance", 30));
    if (rayTraceResult == null) {
      return;
    }

    Block hitBlock = rayTraceResult.getHitBlock();
    if (hitBlock == null) {
      return;
    }

    Location location = hitBlock.getLocation().clone().add(0.5, 0.5, 0.5);
    addPin(location, player, hitBlock);
  }

  protected void addPin(Location location, Player player, Block hitBlock) {
    PinManager.pinList.add(PinUtil.registerPin(player.getDisplayName(), player, location,
        DefaultFactory.DEFAULT));
  }
}
