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
public class RegisterCollegeRequest {

  @Schema(example = "Universidade Federal de Uberlândia", description = "Nome da faculdade.")
  @NotBlank
  private String nome;
}
