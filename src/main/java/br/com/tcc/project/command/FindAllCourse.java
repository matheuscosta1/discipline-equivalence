package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.command.mongo.model.CourseDocument;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindAllCourse extends AbstractCommand<FindAllCourse.Request, List<CourseDocument>> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String nothing;
  }
}
