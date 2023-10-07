package br.com.tcc.project.controller.response;

import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.domain.MenuEquivalence;
import br.com.tcc.project.domain.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class EquivalenceDisciplineResponse {

  @Schema(example = "\"originCollegeDiscipline\": {\n" +
          "        \"id\": \"639944c10b9fe801bf90cbbf\",\n" +
          "        \"workLoad\": 90,\n" +
          "        \"originCode\": \"FAMAT0002\",\n" +
          "        \"course\": \"Ciência da Computação\",\n" +
          "        \"menu\": \"Derivadas; Integrais\",\n" +
          "        \"college\": \"Universidade Federal de Uberlândia\",\n" +
          "        \"name\": \"Cálculo 01\",\n" +
          "        \"program\": \"XPTO\"\n" +
          "    }", description = "Informações da disciplina de origem.")
  private DisciplineDocument originCollegeDiscipline;

  @Schema(example = "\"destinyCollegeDiscipline\": {\n" +
          "        \"id\": \"63a320c3d74b2b7e0a140672\",\n" +
          "        \"workLoad\": 90,\n" +
          "        \"originCode\": \"FAMAT048\",\n" +
          "        \"course\": \"Ciência da Computação\",\n" +
          "        \"menu\": \"Derivadas; Integrais; Limites\",\n" +
          "        \"college\": \"Universidade Federal de Minas Gerais\",\n" +
          "        \"name\": \"Cálculo 01\",\n" +
          "        \"program\": \"XPTO\"\n" +
          "    }", description = "Informações da disciplina de destino.")
  private DisciplineDocument destinyCollegeDiscipline;

  @Schema(example = "true", description = "É carga horária válida.")
  private Boolean isValidWorkLoad;

  @Schema(
      example = "\"menuEquivalence\": {\n" +
              "        \"equivalenceMenu\": [\n" +
              "            \"Derivadas\",\n" +
              "            \"Integrais\"\n" +
              "        ],\n" +
              "        \"nonEquivalenceMenu\": [\n" +
              "            \"Limites\"\n" +
              "        ]\n" +
              "    }",
      description = "Representação das disciplinas que são ou não equivalentes.")
  private MenuEquivalence menuEquivalence;
  @Schema(example = " 66.67", description = "Percentual de disciplinas equivalentes.")
  private BigDecimal menuEquivalencePercentage;

  private Status finalConsideration;
}
