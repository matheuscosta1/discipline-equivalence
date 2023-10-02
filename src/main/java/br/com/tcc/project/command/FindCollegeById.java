package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.command.mongo.model.CollegeDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindCollegeById extends AbstractCommand<FindCollegeById.Request, CollegeDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String collegeId;
  }
}
