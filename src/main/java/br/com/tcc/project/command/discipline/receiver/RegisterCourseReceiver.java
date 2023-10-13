package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.discipline.mapper.RegisterCourseMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.CourseResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterCourse.class)
public class RegisterCourseReceiver
    extends AbstractReceiver<RegisterCourse.Request, CourseResponse> {

  private final RegisterCourseMapper registerCourseMapper =
      Mappers.getMapper(RegisterCourseMapper.class);
  private final CourseDocumentMapper mapper = Mappers.getMapper(CourseDocumentMapper.class);

  @Autowired @Setter private CourseRepository courseRepository;

  @Override
  protected CourseResponse doExecute(RegisterCourse.Request parameter) {
    CourseDocument courseDocument = courseRepository.save(registerCourseMapper.map(parameter));
    return mapper.map(courseDocument);
  }
}
