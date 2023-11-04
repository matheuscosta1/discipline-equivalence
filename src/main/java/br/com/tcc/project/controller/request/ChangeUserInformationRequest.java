package br.com.tcc.project.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class ChangeUserInformationRequest {

  @Schema(example = "E-mail", description = "E-mail atual do usuário.")
  @NotBlank
  private String email;

  @Schema(example = "E-mail", description = "Novo e-mail do usuário.")
  @NotBlank
  private String novoEmail;

  @Schema(example = "E-mail", description = "Nome do usuário.")
  @NotBlank
  private String nome;

  @Schema(example = "Senha atual", description = "Senha atual do usuário.")
  @NotBlank
  private String senhaAtual;

  @Schema(example = "Nova senha", description = "Nova senha do usuário.")
  @NotBlank
  private String novaSenha;
}
