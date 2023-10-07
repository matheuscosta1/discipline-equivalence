package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.FindAllCourseByCollege;
import br.com.tcc.project.command.repositoy.model.CourseDocument;

import java.text.MessageFormat;
import java.util.List;

import br.com.tcc.project.response.CourseResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindAllCourseByCollege.class)
public class FindAllCourseByCollegeIdReceiver
    extends AbstractReceiver<FindAllCourseByCollege.Request, List<CourseResponse>> {

  @Autowired @Setter private CourseRepository courseRepository;
  @Autowired @Setter private CollegeRepository collegeRepository;
  private final CourseDocumentMapper mapper = Mappers.getMapper(CourseDocumentMapper.class);

  @Override
  protected List<CourseResponse> doExecute(FindAllCourseByCollege.Request parameter) {
    CollegeDocument collegeDocument = collegeRepository.findByName(parameter.getCollegeName());
    if(collegeDocument == null) {
      throw new CollegeNotFoundException(
              MessageFormat.format(DisciplineEquivalenceErrors.DEE0001.message(), parameter.getCollegeName()),
              DisciplineEquivalenceErrors.DEE0001.name(),
              DisciplineEquivalenceErrors.DEE0001.group());
    }

    return mapper.map(courseRepository.findAllByCollegeId(collegeDocument.getId()));
  }
}