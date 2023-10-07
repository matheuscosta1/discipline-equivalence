package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllCourse;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.CourseResponse;
import java.util.List;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindAllCourse.class)
public class FindAllCourseReceiver
    extends AbstractReceiver<FindAllCourse.Request, List<CourseResponse>> {

  @Autowired @Setter private CourseRepository courseRepository;
  private final CourseDocumentMapper mapper = Mappers.getMapper(CourseDocumentMapper.class);

  @Override
  protected List<CourseResponse> doExecute(FindAllCourse.Request parameter) {

    return mapper.map(courseRepository.findAll());
  }
}
