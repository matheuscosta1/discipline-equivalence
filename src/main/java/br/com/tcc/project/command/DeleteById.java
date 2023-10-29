package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class DeleteById
    extends AbstractCommand<DeleteById.Request, Void> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    Object genericClass;
    private Integer id;
    private Integer analysisId;
  }
}
