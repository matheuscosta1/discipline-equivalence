package br.com.tcc.project.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class DisciplineResponse {
  private Integer id;
  private String nome;
  private String codigoOrigem;
  private String ementa;
  private String programa;
  private String cargaHoraria;
  private String nomeCurso;
  private String nomeFaculdade;
  private Integer faculdadeId;
  private Integer cursoId;
}
