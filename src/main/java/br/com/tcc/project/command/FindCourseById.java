package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindCourseById extends AbstractCommand<FindCourseById.Request, CourseDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer courseId;
  }
}
