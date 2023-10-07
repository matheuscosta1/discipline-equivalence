package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.discipline.mapper.RegisterCourseMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterCourse.class)
public class RegisterCourseReceiver extends AbstractReceiver<RegisterCourse.Request, Void> {

  private final RegisterCourseMapper registerCourseMapper =
      Mappers.getMapper(RegisterCourseMapper.class);

  @Autowired @Setter private CourseRepository courseRepository;

  @Override
  protected Void doExecute(RegisterCourse.Request parameter) {
    courseRepository.save(registerCourseMapper.map(parameter));
    return null;
  }
}
