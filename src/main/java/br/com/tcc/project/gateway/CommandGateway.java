package br.com.tcc.project.gateway;

import br.com.tcc.project.command.interfaces.Command;
import br.com.tcc.project.command.interfaces.CommandListener;
import br.com.tcc.project.gateway.exception.GatewayException;

public interface CommandGateway {
  String BEAN_NAME = "commandGateway";

  <P, R> R invoke(Class<? extends Command<P, R>> commandClass, P parameter) throws GatewayException;

  <P, R> R invoke(Class<? extends Command<P, R>> commandClass, P parameter, String traceId)
      throws GatewayException;

  void setCommandListener(CommandListener commandListener);

  @SuppressWarnings("java:S3740")
  void setCommandFactory(CommandFactory commandFactory);
}
