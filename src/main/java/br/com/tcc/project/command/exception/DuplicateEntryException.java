package br.com.tcc.project.command.exception;

import br.com.tcc.project.gateway.exception.GatewayException;

public class DuplicateEntryException extends GatewayException {

  public DuplicateEntryException(String message, String code, int group) {
    super(message, code, group);
  }

  public DuplicateEntryException(String message, String code, int group, Throwable cause) {
    super(message, code, group, cause);
  }
}
