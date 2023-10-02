package br.com.tcc.project.exception;

public final class IllegalArgumentExceptionThrowHelper {

  private IllegalArgumentExceptionThrowHelper() {}

  public static void throwIfMissingRequiredArgument(String argumentname, Object argumentValue) {
    if (argumentValue == null) {
      throwMissingRequiredIllegalArgumentException(argumentname);
    }
  }

  private static void throwMissingRequiredIllegalArgumentException(String argumentName) {
    throw new IllegalArgumentException(
        String.format("Required argument '%s' was not provided.", argumentName));
  }
}
