package br.com.tcc.project.command.exception;

import br.com.tcc.project.gateway.exception.GatewayException;

public class UserException extends GatewayException {

  public UserException(String message, String code, int group) {
    super(message, code, group);
  }

  public UserException(String message, String code, int group, Throwable cause) {
    super(message, code, group, cause);
  }
}
