package io.github.usbharu.venriplugin;

import io.github.usbharu.venriplugin.util.MinecraftBlockUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Door.Hinge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class DoorOpener implements Listener {

  @EventHandler
  public void onDoorOpen(PlayerInteractEvent event) {
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
      return;
    }

    if (event.getClickedBlock() == null) {
      return;
    }
    if (MinecraftBlockUtil.isDoor(event.getClickedBlock().getType())) {
      System.out.println(event.getClickedBlock().getBlockData());
      Door door = (Door) event.getClickedBlock().getBlockData();
      Vector vector = null;
      if (door.getFacing().getModX() == 0) {
        if (door.getHinge() == Hinge.LEFT) {
          vector = new Vector(-1, 0, 0);
        } else if (door.getHinge() == Hinge.RIGHT) {
          vector = new Vector(1, 0, 0);
        }else {
          return;
        }

      } else if (door.getFacing().getModZ() == 0) {
        if (door.getHinge() == Hinge.LEFT) {
          vector = new Vector(0, 0, -1);
        } else if (door.getHinge() == Hinge.RIGHT) {
          vector = new Vector(0, 0, 1);
        }else {
          return;
        }
      }else {
        return;
      }
      Block block = event.getClickedBlock().getLocation().clone().add(vector).getBlock();
      if (MinecraftBlockUtil.isDoor(block.getType())) {
        Door door1 = (Door) block.getBlockData();
        door1.setOpen(!door.isOpen());
        block.setBlockData(door1);
      }
    }
  }
}
