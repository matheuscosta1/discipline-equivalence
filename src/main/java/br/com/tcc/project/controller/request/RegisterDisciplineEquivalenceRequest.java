package br.com.tcc.project.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class RegisterDisciplineEquivalenceRequest {
  @Schema(example = "GBC057", description = "Código da disciplina na sua faculdade de origem.")
  @NotBlank
  private String originCode;

  @Schema(example = "GBC059", description = "Código da disciplina na sua faculdade de destino.")
  @NotBlank
  private String destinyCode;
}
