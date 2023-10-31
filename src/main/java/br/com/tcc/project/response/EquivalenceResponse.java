package br.com.tcc.project.response;

import lombok.*;
import org.mapstruct.Mapping;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class EquivalenceResponse {
  private Integer id;
  private String codigoDisciplinaOrigem;
  private String nomeDisciplinaOrigem;
  private String faculdadeOrigem;
  private String cursoOrigem;
  private String codigoDisciplinaDestino;
  private String nomeDisciplinaDestino;
  private String faculdadeDestino;
  private String cursoDestino;
  private String nomeProfessor;
  private String equivalente;
  private String status;
  private String dataCriacao;
  private String justificativa;
}
