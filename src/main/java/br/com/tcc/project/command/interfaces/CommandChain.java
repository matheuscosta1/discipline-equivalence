package br.com.tcc.project.command.interfaces;

public interface CommandChain<P, R> extends Command<P, R> {
  <T, V> V invoke(Class<? extends Command<T, V>> commandClass, T parameters);
}
