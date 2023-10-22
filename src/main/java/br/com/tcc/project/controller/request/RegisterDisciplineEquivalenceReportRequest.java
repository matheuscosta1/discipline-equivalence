package br.com.tcc.project.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class RegisterDisciplineEquivalenceReportRequest {
  @Schema(example = "GBC057", description = "Código da disciplina na sua faculdade de origem.")
  @NotNull
  private Integer idDisciplinaOrigem;

  @Schema(example = "GBC059", description = "Código da disciplina na sua faculdade de destino.")
  @NotNull
  private Integer idDisciplinaDestino;
}
