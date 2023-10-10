package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllDisciplineByCollegeAndCourse;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.mapper.DisciplineDocumentMapper;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import java.util.List;

import br.com.tcc.project.response.DisciplineResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@CommandReceiver(FindAllDisciplineByCollegeAndCourse.class)
public class FindAllDisciplineByCollegeAndCourseReceiver
    extends AbstractReceiver<
        FindAllDisciplineByCollegeAndCourse.Request, Page<DisciplineResponse>> {

  @Autowired @Setter private DisciplineRepository disciplineRepository;
  private final DisciplineDocumentMapper mapper = Mappers.getMapper(DisciplineDocumentMapper.class);

  @Override
  protected Page<DisciplineResponse> doExecute(
      FindAllDisciplineByCollegeAndCourse.Request parameter) {

    PageRequest pageRequest = PageRequest.of(parameter.getPagina(), parameter.getPaginas(), Sort.Direction.valueOf(parameter.getDirection()), parameter.getOrderBy());
    List<DisciplineResponse> courseResponses;

    if (parameter.getNome() != null && !parameter.getNome().isBlank()) {
      Page<DisciplineDocument> courseDocumentPage = disciplineRepository.findByNomeContaining(parameter.getNome(), pageRequest);

      courseResponses = mapper.map(courseDocumentPage.getContent());
      return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
    }

    if(parameter.getFaculdadeId() != null && parameter.getCursoId() != null) {
      Page<DisciplineDocument> courseDocumentPage = disciplineRepository.findByFaculdadeIdAndCursoId(Integer.valueOf(parameter.getFaculdadeId()), Integer.valueOf(parameter.getCursoId()), pageRequest);
      courseResponses = mapper.map(courseDocumentPage.getContent());
      return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
    }

    Page<DisciplineDocument> courseDocumentPage = disciplineRepository.findAll(pageRequest);
    courseResponses = mapper.map(courseDocumentPage.getContent());

    return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
  }
}
