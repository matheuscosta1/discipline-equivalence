package br.com.tcc.project.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProfessorResponse {
  private Integer id;
  private String nome;
  private Integer faculdadeId;
  private Integer cursoId;
  private Integer disciplinaId;
  private String nomeFaculdade;
  private String nomeCurso;
  private String nomeDisciplina;
  private String email;
}
