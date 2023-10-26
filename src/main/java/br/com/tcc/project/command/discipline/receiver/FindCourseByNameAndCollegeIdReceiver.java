package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindCourseById;
import br.com.tcc.project.command.FindCourseByNameAndCollegeId;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindCourseByNameAndCollegeId.class)
public class FindCourseByNameAndCollegeIdReceiver
    extends AbstractReceiver<FindCourseByNameAndCollegeId.Request, CourseDocument> {

  @Autowired @Setter private CourseRepository courseRepository;

  @Override
  protected CourseDocument doExecute(FindCourseByNameAndCollegeId.Request parameter) {

    return courseRepository
        .findByNomeAndFaculdadeId(parameter.getName(), parameter.getCollegeId());
  }
}
