package br.com.tcc.project.command.exception;

public class ReceiverNotSetException extends IllegalStateException {
  public ReceiverNotSetException(String s) {
    super(s);
  }
}
