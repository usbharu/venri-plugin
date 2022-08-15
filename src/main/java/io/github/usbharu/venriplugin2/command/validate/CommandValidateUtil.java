package io.github.usbharu.venriplugin2.command.validate;

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
}
