package br.com.tcc.project.gateway.impl;

import br.com.tcc.project.command.impl.DefaultInvoker;
import br.com.tcc.project.command.interfaces.Command;
import br.com.tcc.project.command.interfaces.CommandChain;
import br.com.tcc.project.command.interfaces.CommandChainReceiver;
import br.com.tcc.project.command.interfaces.CommandListener;
import br.com.tcc.project.command.interfaces.Invoker;
import br.com.tcc.project.gateway.CommandFactory;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.gateway.ReceiverResolver;
import java.util.UUID;

public class DefaultCommandGateway implements CommandGateway, CommandChainReceiver {

  private final ReceiverResolver receiverResolver;
  private CommandListener commandListener;

  @SuppressWarnings("java:S3740")
  private CommandFactory commandFactory = new ReflectionCommandFactory();

  public DefaultCommandGateway(ReceiverResolver receiverResolver) {
    this(receiverResolver, true);
  }

  public DefaultCommandGateway(ReceiverResolver receiverResolver, boolean bindAsChainReceiver) {
    this.receiverResolver = receiverResolver;
    if (bindAsChainReceiver) {
      this.receiverResolver.bind(CommandChain.class, this);
    }
  }

  @Override
  public <P, R> R invoke(Class<? extends Command<P, R>> commandClass, P parameter) {
    return this.invoke(commandClass, parameter, null);
  }

  @Override
  public <P, R> R invoke(Class<? extends Command<P, R>> commandClass, P parameter, String traceId) {
    Command<P, R> command = (Command<P, R>) commandFactory.apply(commandClass);
    command.setReceiver(receiverResolver.resolve(commandClass));
    command.setTraceId(traceId);
    Invoker<P, R> invoker = new DefaultInvoker<>();
    invoker
        .setExecutionId(UUID.randomUUID().toString())
        .setCommand(command)
        .setParameter(parameter)
        .setCommandListener(commandListener)
        .invoke();
    return invoker.getResult();
  }

  public void setCommandListener(CommandListener commandListener) {
    this.commandListener = commandListener;
  }

  public void setCommandFactory(CommandFactory commandFactory) {
    this.commandFactory = commandFactory;
  }
}
