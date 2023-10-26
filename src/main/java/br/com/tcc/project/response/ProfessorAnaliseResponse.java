package br.com.tcc.project.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProfessorAnaliseResponse {
  private Integer id;
  private Integer faculdadeOrigemId;
  private Integer cursoOrigemId;
  private Integer disciplinaOrigemId;
  private Integer faculdadeDestinoId;
  private Integer cursoDestinoId;
  private Integer disciplinaDestinoId;
  private Integer professorId;
  private String nomeProfessor;
  private String nomeFaculdadeOrigem;
  private String nomeCursoOrigem;
  private String nomeDisciplinaOrigem;
  private String nomeFaculdadeDestino;
  private String nomeCursoDestino;
  private String nomeDisciplinaDestino;
  private String dataMaxima;
  private String status;
  private String nomeAluno;
  private String emailAluno;
}
