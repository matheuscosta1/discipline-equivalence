package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindCourseByNameAndCollegeId extends AbstractCommand<FindCourseByNameAndCollegeId.Request, CourseDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer collegeId;
    private String name;
  }
}
