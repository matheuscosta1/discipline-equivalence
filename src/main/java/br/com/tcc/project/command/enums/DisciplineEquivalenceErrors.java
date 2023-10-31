package br.com.tcc.project.command.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DisciplineEquivalenceErrors {
  DEE0001("Não foi possível buscar os cursos pela faculdade: {0}.", 1000),
  DEE0002("Não foi possível buscar a faculdade pelo id: {0}.", 1000),
  DEE0003("Não foi possível buscar o curso pelo id: {0}.", 1000),
  DEE0004("Não foi possível buscar a disciplina pelo id: {0}.", 1000),
  DEE0006("Usuário não existe.", 1000),
  DEE0007("Equivalência já foi registrada.", 1000),
  DEE0008("Análise de equivalência já foi registrada.", 1000),
  DEE0009("Curso já registrado.", 1000),
  DEE0010("Professor já registrado com esse e-mail.", 1000),
  DEE0011("Disciplina já registrada.", 1000),
  DEE0012("Análise de equivalência realizada, não é possível deletar.", 1000),
  DEE0013("Não é possível realizar o registro de equivalência para uma análise que não está pendente ou não existe.", 1000);


  private final String message;
  private final int group;

  public String message() {
    return message;
  }

  public int group() {
    return group;
  }
}
