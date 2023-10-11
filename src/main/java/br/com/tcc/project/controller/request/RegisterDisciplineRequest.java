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
public class RegisterDisciplineRequest {
  @Schema(example = "60", description = "Carga horária da disciplina.")
  @NotNull
  private Integer cargaHoraria;

  @Schema(example = "GBC057", description = "Código da disciplina na sua faculdade de origem.")
  @NotBlank
  private String codigoOrigem;

  @Schema(
      example = "Variáveis; Comandos condicionais; Estrutura de repetição; Funções; Ponteiros",
      description = "Ementa da disciplina.")
  @NotBlank
  private String ementa;

  @Schema(example = "Programação Procedimental", description = "Nome da disciplina.")
  @NotBlank
  private String nome;

  @Schema(example = "Abobrinha..", description = "Programa da disciplina.")
  @NotBlank
  private String programa;

  @Schema(example = "Universidade Federal de Uberlândia", description = "Nome da faculdade.")
  @NotNull
  private Integer faculdadeId;

  @Schema(example = "Ciência da Computação", description = "Nome do curso.")
  @NotNull
  private Integer cursoId;
}
