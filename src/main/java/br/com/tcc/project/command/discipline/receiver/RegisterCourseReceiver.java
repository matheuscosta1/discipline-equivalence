package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.RegisterCourse;
import br.com.tcc.project.command.discipline.mapper.RegisterCourseMapper;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@CommandReceiver(RegisterCourse.class)
public class RegisterCourseReceiver extends AbstractReceiver<RegisterCourse.Request, Void> {

  private final RegisterCourseMapper registerCourseMapper =
      Mappers.getMapper(RegisterCourseMapper.class);

  @Autowired @Setter private MongoTemplate mongoTemplate;

  @Override
  protected Void doExecute(RegisterCourse.Request parameter) {
    mongoTemplate.save(registerCourseMapper.map(parameter));
    return null;
  }
}
