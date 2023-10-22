package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterEquivalence
    extends AbstractCommand<RegisterEquivalence.Request, EquivalenceDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer id;
    private String justification;
    private Boolean equivalent;
    private AnalisesDocument analisesDocument;
  }
}
