package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindAnalysisByOriginAndDestinyId
    extends AbstractCommand<FindAnalysisByOriginAndDestinyId.Request, AnalisesDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer collegeOriginId;
    private Integer collegeDestinyId;
    private Integer disciplineOriginId;
    private Integer disciplineDestinyId;
    private String status;
  }
}
