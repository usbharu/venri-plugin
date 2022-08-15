package io.github.usbharu.venriplugin2.command.validate;

public enum ValidatedInfo {
  MANY_ARGUMENTS("Many arguments"),
  FEW_ARGUMENTS("Few arguments"),
  ARGUMENTS_NOT_IN_RANGE("Arguments not in rage"),
  ILLEGAL_ARGUMENT_LENGTH("Argument length is abnormal"),
  ILLEGAL_ARGUMENT("Invalid argument"),
  NOT_PLAYER("Not Player"),
  SUCCESS("");


  private final String message;

  ValidatedInfo(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
