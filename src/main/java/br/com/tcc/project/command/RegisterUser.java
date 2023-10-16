package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterUser extends AbstractCommand<RegisterUser.Request, UsuarioDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer id;
    private String nome;
    private String email;
    private Integer perfil;
    private String password;
  }
}
