package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllCourse;
import br.com.tcc.project.command.impl.AbstractReceiver;
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

@CommandReceiver(FindAllCourse.class)
public class FindAllCourseReceiver
    extends AbstractReceiver<FindAllCourse.Request, Page<CourseResponse>> {

  @Autowired @Setter private CourseRepository courseRepository;
  private final CourseDocumentMapper mapper = Mappers.getMapper(CourseDocumentMapper.class);

  @Override
  protected Page<CourseResponse> doExecute(FindAllCourse.Request parameter) {
    PageRequest pageRequest = PageRequest.of(parameter.getPagina(), parameter.getPaginas(), Sort.Direction.valueOf(parameter.getDirection()), parameter.getOrderBy());
    List<CourseResponse> courseResponses;

    if (parameter.getNome() != null && !parameter.getNome().isBlank()) {
      Page<CourseDocument> courseDocumentPage = courseRepository.findByNomeContaining(parameter.getNome(), pageRequest);
      courseResponses = mapper.map(courseDocumentPage.getContent());
      return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
    }


    if(parameter.getFaculdadeId() != null) {
      Page<CourseDocument> courseDocumentPage = courseRepository.findByFaculdadeId(Integer.valueOf(parameter.getFaculdadeId()), pageRequest);
      courseResponses = mapper.map(courseDocumentPage.getContent());
      return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
    }

    Page<CourseDocument> courseDocumentPage = courseRepository.findAll(pageRequest);
    courseResponses = mapper.map(courseDocumentPage.getContent());

    return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
  }
}
