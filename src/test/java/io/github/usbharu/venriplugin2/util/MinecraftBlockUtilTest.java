package io.github.usbharu.venriplugin2.util;

import static org.bukkit.Material.ACACIA_DOOR;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MinecraftBlockUtilTest {

  @Test
  void isDoor_Door_returnTrue() {
    boolean door = MinecraftBlockUtil.isDoor(ACACIA_DOOR);
    assertTrue(door);
  }
}
