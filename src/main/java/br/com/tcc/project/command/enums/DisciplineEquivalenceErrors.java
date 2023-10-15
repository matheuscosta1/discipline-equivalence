package br.com.tcc.project.command.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DisciplineEquivalenceErrors {
  DEE0001("Não foi possível buscar os cursos pela faculdade: {0}.", 1000),
  DEE0002("Não foi possível buscar a faculdade pelo id: {0}.", 1000),
  DEE0003("Não foi possível buscar o curso pelo id: {0}.", 1000),
  DEE0004("Não foi possível buscar a disciplina pelo id: {0}.", 1000),
  DEE0005("Não é possível autorizar com esse token. Revise se sua autorização contém os perfis corretos para acessar o recurso.", 1000);

  private final String message;
  private final int group;

  public String message() {
    return message;
  }

  public int group() {
    return group;
  }
}
