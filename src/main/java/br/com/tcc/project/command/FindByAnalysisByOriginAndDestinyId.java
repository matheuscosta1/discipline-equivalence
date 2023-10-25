package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@GenerateCommandFactory
public class FindByAnalysisByOriginAndDestinyId
    extends AbstractCommand<FindByAnalysisByOriginAndDestinyId.Request, AnalisesDocument> {

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
