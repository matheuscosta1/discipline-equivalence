package br.com.tcc.project.command.exception;

public class ParameterNotSetException extends IllegalStateException {
  public ParameterNotSetException(String s) {
    super(s);
  }
}
