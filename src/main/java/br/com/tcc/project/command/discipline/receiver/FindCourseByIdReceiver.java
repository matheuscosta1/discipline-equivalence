package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindCollegeById;
import br.com.tcc.project.command.FindCourseById;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindCourseById.class)
public class FindCourseByIdReceiver
    extends AbstractReceiver<FindCourseById.Request, CourseDocument> {

  @Autowired @Setter private CourseRepository courseRepository;

  @Override
  protected CourseDocument doExecute(FindCourseById.Request parameter) {

    return courseRepository
        .findById(parameter.getCourseId())
        .orElseThrow(
            () ->
                new CollegeNotFoundException(
                    MessageFormat.format(
                        DisciplineEquivalenceErrors.DEE0003.message(), parameter.getCourseId()),
                    DisciplineEquivalenceErrors.DEE0003.name(),
                    DisciplineEquivalenceErrors.DEE0003.group()));
  }
}
