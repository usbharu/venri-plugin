package io.github.usbharu.venriplugin.util;

import org.bukkit.Material;

public class MinecraftBlockUtil {

  private MinecraftBlockUtil() {
    // Do nothing
  }

  public static boolean isDoor(Material material) {
    return material == Material.ACACIA_DOOR || material == Material.BIRCH_DOOR || material == Material.DARK_OAK_DOOR  || material == Material.JUNGLE_DOOR || material == Material.SPRUCE_DOOR || material == Material.OAK_DOOR;
  }

}
