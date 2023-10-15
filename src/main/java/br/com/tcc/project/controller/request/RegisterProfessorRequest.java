package br.com.tcc.project.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class RegisterProfessorRequest {

  @Schema(example = "Alberto Luis", description = "Nome do professor.")
  @NotBlank
  private String nome;

  @Schema(example = "Alberto Luis", description = "Nome do professor.")
  @NotBlank
  private String email;

  @Schema(
      example = "Universidade Federal de Uberlândia",
      description = "Identificador da faculdade")
  @NotNull
  private Integer faculdadeId;

  @Schema(example = "Ciência da Computação", description = "Identificador do curso")
  @NotNull
  private Integer cursoId;

  @Schema(example = "Ciência da Computação", description = "Identificador da disciplina")
  @NotNull
  private Integer disciplinaId;
}
