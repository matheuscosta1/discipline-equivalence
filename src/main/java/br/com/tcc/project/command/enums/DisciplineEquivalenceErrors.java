package br.com.tcc.project.command.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DisciplineEquivalenceErrors {
  DEE0001("Não foi possível buscar os cursos pela faculdade: {0}.", 1000);

  private final String message;
  private final int group;

  public String message() {
    return message;
  }

  public int group() {
    return group;
  }
}
