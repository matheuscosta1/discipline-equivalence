package br.com.tcc.project.command.exception;

public class CommandNotSetException extends IllegalStateException {
  public CommandNotSetException(String s) {
    super(s);
  }
}
