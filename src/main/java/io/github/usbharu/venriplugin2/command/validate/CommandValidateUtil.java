package io.github.usbharu.venriplugin2.command.validate;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandValidateUtil {

  private CommandValidateUtil() {
  }

  public static boolean verifyLength(String[] args, int length) {
    return args.length == length;
  }

  public static boolean verifyMaxLength(String[] args, int length) {
    return args.length <= length;
  }

  public static boolean verifyMinLength(String[] args, int length) {
    return args.length >= length;
  }

  public static boolean isPlayer(CommandSender sender) {
    return sender instanceof Player;
  }

  public static Player getPlayer(CommandSender sender) {
    if (sender instanceof Player) {
      return ((Player) sender);
    }
    return null;
  }
}
