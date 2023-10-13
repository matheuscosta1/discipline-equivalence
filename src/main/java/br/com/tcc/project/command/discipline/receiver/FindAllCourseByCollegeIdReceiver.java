package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllCourseByCollege;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.CourseRepository;
import br.com.tcc.project.command.repositoy.mapper.CourseDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.CourseResponse;
import java.util.List;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@CommandReceiver(FindAllCourseByCollege.class)
public class FindAllCourseByCollegeIdReceiver
    extends AbstractReceiver<FindAllCourseByCollege.Request, Page<CourseResponse>> {

  @Autowired @Setter private CourseRepository courseRepository;
  @Autowired @Setter private CollegeRepository collegeRepository;
  private final CourseDocumentMapper mapper = Mappers.getMapper(CourseDocumentMapper.class);

  @Override
  protected Page<CourseResponse> doExecute(FindAllCourseByCollege.Request parameter) {
    PageRequest pageRequest =
        PageRequest.of(
            parameter.getPagina(),
            parameter.getPaginas(),
            Sort.Direction.valueOf(parameter.getDirection()),
            parameter.getOrderBy());
    List<CourseResponse> courseResponses;

    Page<CourseDocument> courseDocumentPage =
        courseRepository.findByFaculdadeId(parameter.getFaculdadeId(), pageRequest);
    courseResponses = mapper.map(courseDocumentPage.getContent());
    return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
  }
}
