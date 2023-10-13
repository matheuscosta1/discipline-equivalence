package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindCourseById;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import java.text.MessageFormat;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindCourseById.class)
public class FindCourseByIdReceiver
    extends AbstractReceiver<FindCourseById.Request, CourseDocument> {

  @Autowired @Setter private CourseRepository courseRepository;
  private final CourseDocumentMapper mapper = Mappers.getMapper(CourseDocumentMapper.class);

  @Override
  protected CourseDocument doExecute(FindCourseById.Request parameter) {

    return courseRepository
        .findById(parameter.getCursoId())
        .orElseThrow(
            () ->
                new CollegeNotFoundException(
                    MessageFormat.format(
                        DisciplineEquivalenceErrors.DEE0003.message(), parameter.getCursoId()),
                    DisciplineEquivalenceErrors.DEE0003.name(),
                    DisciplineEquivalenceErrors.DEE0003.group()));
  }
}
