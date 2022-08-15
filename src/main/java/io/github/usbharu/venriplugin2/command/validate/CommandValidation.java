package io.github.usbharu.venriplugin2.command.validate;

import static io.github.usbharu.venriplugin2.command.validate.Validate.builder;
import static io.github.usbharu.venriplugin2.command.validate.Validate.failedBuilder;
import static io.github.usbharu.venriplugin2.command.validate.Validate.failedBuilderWithMessage;
import static io.github.usbharu.venriplugin2.command.validate.Validate.success;
import static io.github.usbharu.venriplugin2.command.validate.ValidatedInfo.ARGUMENTS_NOT_IN_RANGE;
import static io.github.usbharu.venriplugin2.command.validate.ValidatedInfo.ILLEGAL_ARGUMENT;


public class CommandValidation {

  private CommandValidation() {
  }

  public static Validate verifyLength(String[] args, int length) {
    return builder(CommandValidateUtil.verifyLength(args, length), ARGUMENTS_NOT_IN_RANGE);
  }

  public static Validate verifyArgs(String[] args, String first, String... seconds) {
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

  public static Validate verifyArg(String[] args, int index, String expected, String... or) {
    boolean result = args[index].equals(expected);
    for (int i = 1, len = or.length + 1; i < len; i++) {
      if (result) {
        break;
      }
      result = args[index].equals(or[i]);
    }
    return builder(result, ILLEGAL_ARGUMENT, args[index]);
  }
}
