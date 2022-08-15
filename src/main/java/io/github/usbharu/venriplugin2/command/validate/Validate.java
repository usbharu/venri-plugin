package io.github.usbharu.venriplugin2.command.validate;


public class Validate {

  private final boolean validateResult;
  private final io.github.usbharu.venriplugin2.command.validate.ValidatedInfo validatedInfo;

  private final String message;

  public Validate(boolean validateResult, ValidatedInfo validatedInfo) {
    this.validateResult = validateResult;
    this.validatedInfo = validatedInfo;
    this.message = "";
  }

  public Validate(boolean validateResult, ValidatedInfo validatedInfo, String message) {
    this.validateResult = validateResult;
    this.validatedInfo = validatedInfo;
    this.message = message;
  }

  public static Validate builder(boolean validateResult, ValidatedInfo validatedInfo) {
    return builder(validateResult, validatedInfo, "");
  }

  public static Validate failedBuilder(ValidatedInfo validatedInfo) {
    return new Validate(false, validatedInfo);
  }

  public static Validate failedBuilder(ValidatedInfo validatedInfo, String message) {
    return new Validate(false, validatedInfo, message);
  }

  public static Validate builder(boolean validateResult, ValidatedInfo validatedInfo,
      String message) {
    if (validateResult) {
      return success(message);
    }
    return new Validate(false, validatedInfo, message);
  }

  public static Validate failedBuilderWithMessage(ValidatedInfo validatedInfo, String message) {
    return new Validate(false, validatedInfo, validatedInfo.getMessage() + " at " + message);
  }

  public static Validate success() {
    return success("");
  }

  public static Validate success(String message) {
    return new Validate(true, ValidatedInfo.SUCCESS, message);
  }

  public boolean wasSuccess() {
    return validateResult;
  }

  public ValidatedInfo getValidatedInfo() {
    return validatedInfo;
  }

  public String getMessage() {
    return message;
  }


}
