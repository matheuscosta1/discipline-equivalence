package br.com.tcc.project.command.interfaces;

public interface Receiver<P, R> {
  R execute(P parameter) throws IllegalStateException;
}
