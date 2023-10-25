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
public class RegisterProfessorAnalysisRequest {
  private Integer id;

  @NotNull
  private String nomeAluno;

  @NotNull
  private String emailAluno;

  @Schema(example = "1", description = "Identificador da faculdade de origem")
  @NotNull
  private Integer faculdadeOrigemId;

  @Schema(example = "1", description = "Identificador do curso de origem")
  @NotNull
  private Integer cursoOrigemId;

  @Schema(example = "1", description = "Identificador da disciplina de origem")
  @NotNull
  private Integer disciplinaOrigemId;

  @Schema(example = "1", description = "Identificador da faculdade de destino")
  @NotNull
  private Integer faculdadeDestinoId;

  @Schema(example = "1", description = "Identificador do curso de destino")
  @NotNull
  private Integer cursoDestinoId;

  @Schema(example = "1", description = "Identificador da disciplina de destino")
  @NotNull
  private Integer disciplinaDestinoId;

  @Schema(example = "1", description = "Identificador do professor")
  @NotNull
  private Integer professorId;

  @Schema(example = "15-05-2000", description = "Identificador da disciplina de destino")
  @NotNull
  public String dataMaxima;

  @NotNull
  private String emailAdministrador;
}
