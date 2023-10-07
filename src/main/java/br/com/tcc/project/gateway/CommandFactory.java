package br.com.tcc.project.gateway;

import br.com.tcc.project.command.interfaces.Command;
import java.util.function.Function;

public interface CommandFactory<T extends Command> extends Function<Class<T>, T> {

  Class<T> getType();
}
