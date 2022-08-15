/*!
* This code was created by PhantomUnicorns and posted at https://bukkit.org/threads/actionbar-util.473004/ with modifications.
No permission has been granted.
Contact twitter@usbharu_dev or usbharu@gmail.com if you have issues.
*
*
* このコードはPhantomUnicornsによって作成され、https://bukkit.org/threads/actionbar-util.473004/ に投稿されたものを改変して使用しています。
許可は得ていません。
問題がある場合は twitter@usbharu_dev または usbharu@gmail.com に連絡してください。
* */


package io.github.usbharu.venriplugin2.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionBar {

  private static final String _VERSION;
  private static final UUID uuid = UUID.randomUUID();
  private static Class<?> _CRAFTPLAYER_CLASS;
  private static Constructor<?> _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR;
  private static Method _A_METHOD, _GETHANDLE_METHOD, _SEND_PACKET_METHOD;
  private static Field _PLAYER_CONNECTION_FIELD;
  private static Object _GAME_INFO_CONSTANT;

  static {
    String name = Bukkit.getServer().getClass().getName();
    name = name.substring(name.indexOf("craftbukkit.") + "craftbukkit.".length());
    _VERSION = name.substring(0, name.indexOf("."));
    try {
      Class<?> _ICHAT_BASE_COMPONENT_CLASS =
          Class.forName("net.minecraft.server." + _VERSION + ".IChatBaseComponent");
      Class<?> _PACKET_PLAY_OUT_CHAT_CLASS =
          Class.forName("net.minecraft.server." + _VERSION + ".PacketPlayOutChat");
      _CRAFTPLAYER_CLASS =
          Class.forName("org.bukkit.craftbukkit." + _VERSION + ".entity.CraftPlayer");
      Class<?> _ENTITYPLAYER_CLASS =
          Class.forName("net.minecraft.server." + _VERSION + ".EntityPlayer");
      Class<?> _PLAYER_CONNECTION_CLASS =
          Class.forName("net.minecraft.server." + _VERSION + ".PlayerConnection");

      if (_VERSION.contains("1.9")) {
        _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR =
            _PACKET_PLAY_OUT_CHAT_CLASS.getConstructor(_ICHAT_BASE_COMPONENT_CLASS, byte.class);
      } else {
        Class<?> _CHAT_MESSAGE_TYPE_ENUM =
            Class.forName("net.minecraft.server." + _VERSION + ".ChatMessageType");
        _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR =
            _PACKET_PLAY_OUT_CHAT_CLASS.getConstructor(_ICHAT_BASE_COMPONENT_CLASS,
                _CHAT_MESSAGE_TYPE_ENUM, Class.forName("java.util.UUID"));
        for (Object obj : _CHAT_MESSAGE_TYPE_ENUM.getEnumConstants()) {
          if (obj.toString().equals("GAME_INFO")) {
            _GAME_INFO_CONSTANT = obj;
          }
        }
      }

      _A_METHOD =
          Class.forName("net.minecraft.server." + _VERSION + ".IChatBaseComponent$ChatSerializer")
              .getMethod("a", String.class);
      _GETHANDLE_METHOD = _CRAFTPLAYER_CLASS.getMethod("getHandle");
      _SEND_PACKET_METHOD = _PLAYER_CONNECTION_CLASS.getMethod("sendPacket",
          Class.forName("net.minecraft.server." + _VERSION + ".Packet"));

      _PLAYER_CONNECTION_FIELD = _ENTITYPLAYER_CLASS.getDeclaredField("playerConnection");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void send(Player player, String message) {
    try {
      Object messageComponent = _A_METHOD.invoke(null,
          "{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
      if (_VERSION.contains("1.9")) {
        Object packet = _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR.newInstance(messageComponent, (byte) 2,
            UUID.randomUUID());
        Object craftPlayer = _CRAFTPLAYER_CLASS.cast(player);
        Object entityPlayer = _GETHANDLE_METHOD.invoke(craftPlayer);
        Object playerConnection = _PLAYER_CONNECTION_FIELD.get(entityPlayer);
        _SEND_PACKET_METHOD.invoke(playerConnection, packet);
      } else {
        Object packet =
            _PACKET_PLAY_OUT_CHAT_CONSTRUCTOR.newInstance(messageComponent, _GAME_INFO_CONSTANT,
                UUID.randomUUID());
        Object craftPlayer = _CRAFTPLAYER_CLASS.cast(player);
        Object entityPlayer = _GETHANDLE_METHOD.invoke(craftPlayer);
        Object playerConnection = _PLAYER_CONNECTION_FIELD.get(entityPlayer);
        _SEND_PACKET_METHOD.invoke(playerConnection, packet);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
