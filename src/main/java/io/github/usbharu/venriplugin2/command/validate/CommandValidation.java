package io.github.usbharu.venriplugin2.command.validate;

import static io.github.usbharu.venriplugin2.command.validate.CommandValidateUtil.isPlayer;
import static io.github.usbharu.venriplugin2.command.validate.Validate.builder;
import static io.github.usbharu.venriplugin2.command.validate.Validate.failedBuilder;
import static io.github.usbharu.venriplugin2.command.validate.Validate.failedBuilderWithMessage;
import static io.github.usbharu.venriplugin2.command.validate.Validate.success;
import static io.github.usbharu.venriplugin2.command.validate.ValidatedInfo.ARGUMENTS_NOT_IN_RANGE;
import static io.github.usbharu.venriplugin2.command.validate.ValidatedInfo.ILLEGAL_ARGUMENT;
import static io.github.usbharu.venriplugin2.command.validate.ValidatedInfo.MANY_ARGUMENTS;
import static io.github.usbharu.venriplugin2.command.validate.ValidatedInfo.NOT_PLAYER;

import org.bukkit.command.CommandSender;


public class CommandValidation {

  private CommandValidation() {
  }

  public static Validate validateLength(String[] args, int length) {
    return builder(CommandValidateUtil.verifyLength(args, length), ARGUMENTS_NOT_IN_RANGE);
  }

  public static Validate validateMaxLength(String[] args, int maxLength) {
    return builder(CommandValidateUtil.verifyMaxLength(args, maxLength), MANY_ARGUMENTS);
  }

  public static Validate validateArgs(String[] args, String first, String... seconds) {
    if (!CommandValidateUtil.verifyLength(args, 1 + seconds.length)) {
      return failedBuilder(ARGUMENTS_NOT_IN_RANGE);
    }
    if (!args[0].equals(first)) {
      return failedBuilderWithMessage(ILLEGAL_ARGUMENT, args[0]);
    }
    for (int i = 0; i < seconds.length; i++) {
      if (!args[i + 1].equals(seconds[i])) {
        return failedBuilderWithMessage(ILLEGAL_ARGUMENT, args[i + 1]);
      }
    }
    return success();
  }

  public static Validate validateArg(String[] args, int index, String expected, String... or) {
    boolean result = args[index].equals(expected);
    for (String s : or) {
      if (result) {
        break;
      }
      result = args[index].equals(s);
    }
    return builder(result, ILLEGAL_ARGUMENT, args[index]);
  }

  public static Validate validatePlayer(CommandSender sender) {
    return builder(isPlayer(sender), NOT_PLAYER, sender.toString());
  }
}
