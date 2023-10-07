package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllDisciplineByCollegeAndCourse;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.command.repositoy.mapper.DisciplineDocumentMapper;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import java.util.List;

import br.com.tcc.project.response.DisciplineResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindAllDisciplineByCollegeAndCourse.class)
public class FindAllDisciplineByCollegeAndCourseReceiver
    extends AbstractReceiver<
        FindAllDisciplineByCollegeAndCourse.Request, List<DisciplineResponse>> {

  @Autowired @Setter private DisciplineRepository disciplineRepository;
  private final DisciplineDocumentMapper mapper = Mappers.getMapper(DisciplineDocumentMapper.class);

  @Override
  protected List<DisciplineResponse> doExecute(
      FindAllDisciplineByCollegeAndCourse.Request parameter) {

    return mapper.map(disciplineRepository.findByCollegeIdAndCourseId(
            parameter.getCollegeId(), parameter.getCourseId()));
  }
}
