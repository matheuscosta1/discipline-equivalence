package br.com.tcc.project.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AnaliseEquivalenciaDisciplineResponse {
  private Integer id;
  private String nome;
  private String codigoOrigem;
  private String ementa;
  private String programa;
  private String cargaHoraria;
  private Integer faculdadeId;
  private String nomeFaculdade;
  private String nomeCurso;
  private Integer cursoId;
}
