package br.com.tcc.project.command.interfaces;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("java:S1452")
public interface CommandListener {

  default Consumer<Command<?, ?>> beforeExecute() {
    return command -> {};
  }

  default Consumer<Command<?, ?>> afterExecute() {
    return command -> {};
  }

  default BiConsumer<Command<?, ?>, Throwable> onError() {
    return (command, throwable) -> {};
  }
}
