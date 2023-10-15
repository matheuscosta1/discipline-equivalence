package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@GenerateCommandFactory
public class UpdateUserPassword extends AbstractCommand<UpdateUserPassword.Request, UserDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private UserDocument userDocument;
  }
}
