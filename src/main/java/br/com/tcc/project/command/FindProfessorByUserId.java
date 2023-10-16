package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindProfessorByUserId extends AbstractCommand<FindProfessorByUserId.Request, ProfessorDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer id;
  }
}
