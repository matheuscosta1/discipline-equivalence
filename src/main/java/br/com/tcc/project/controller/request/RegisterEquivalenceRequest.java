package br.com.tcc.project.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class RegisterEquivalenceRequest {

  @Schema(example = "Alberto Luis", description = "Nome do professor.")
  @NotBlank
  private String justificativa;

  @Schema(
          example = "Universidade Federal de Uberlândia",
          description = "Identificador da faculdade")
  @NotNull
  private Boolean equivalente;

  @Schema(
      example = "Universidade Federal de Uberlândia",
      description = "Identificador da faculdade")
  @NotNull
  private Integer disciplinaOrigemId;

  @Schema(example = "Ciência da Computação", description = "Identificador do curso")
  @NotNull
  private Integer disciplinaDestinoId;

  @Schema(
          example = "Universidade Federal de Uberlândia",
          description = "Identificador da faculdade")
  @NotNull
  private Integer faculdadeOrigemId;

  @Schema(example = "Ciência da Computação", description = "Identificador do curso")
  @NotNull
  private Integer faculdadeDestinoId;
}
