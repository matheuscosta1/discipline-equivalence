package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindUserByEmail extends AbstractCommand<FindUserByEmail.Request, UsuarioDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String email;
  }
}
